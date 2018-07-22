package rocks.cleanstone.player.listener;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.core.config.MinecraftConfig;
import rocks.cleanstone.data.vanilla.nbt.NamedBinaryTag;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.net.packet.data.EntityMetadata;
import rocks.cleanstone.net.packet.enums.MobType;
import rocks.cleanstone.net.packet.outbound.ChunkDataPacket;
import rocks.cleanstone.net.packet.outbound.SpawnMobPacket;
import rocks.cleanstone.net.packet.outbound.UnloadChunkPacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.event.PlayerMoveEvent;
import rocks.cleanstone.player.event.PlayerQuitEvent;

public class PlayerMoveChunkLoadListener {

    private final Multimap<UUID, Pair<Integer, Integer>> playerHasLoaded = ArrayListMultimap.create();
    private final Map<UUID, Object> lockMap = new ConcurrentHashMap<>();
    private final Map<UUID, AtomicInteger> updateCounterMap = new ConcurrentHashMap<>();
    private final MinecraftConfig minecraftConfig;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public PlayerMoveChunkLoadListener(MinecraftConfig minecraftConfig) {
        this.minecraftConfig = minecraftConfig;
    }

    @Async("playerExec")
    @EventListener
    public void onPlayerMove(PlayerMoveEvent playerMoveEvent) {
        final int chunkX = playerMoveEvent.getNewPosition().getXAsInt() >> 4;
        final int chunkY = playerMoveEvent.getNewPosition().getZAsInt() >> 4;

        final Player player = playerMoveEvent.getPlayer();
        UUID uuid = player.getID().getUUID();

        // reject unneeded updates early
        if (chunkUpdateNotNeeded(playerMoveEvent, chunkX, chunkY, uuid)) {
            return;
        }

        int initialValue = updateCounterMap.computeIfAbsent(uuid, k -> new AtomicInteger(0)).incrementAndGet();
        synchronized (lockMap.computeIfAbsent(uuid, k -> new Object())) {
            // return early if the player already moved further
            if (initialValue != updateCounterMap.get(uuid).get()) {
                return;
            }
            // check again because the chunk could already have been loaded inside synchronized block
            if (chunkUpdateNotNeeded(playerMoveEvent, chunkX, chunkY, uuid)) {
                return;
            }

            logger.info("loading chunks around {}, {} for {}", chunkX, chunkY, player.getID().getName());
            sendNewNearbyChunks(player, chunkX, chunkY, initialValue);
            unloadRemoteChunks(player, chunkX, chunkY);
            logger.info("done loading chunks for {}", player.getID().getName());
        }
    }

    protected boolean chunkUpdateNotNeeded(PlayerMoveEvent playerMoveEvent, int chunkX, int chunkY, UUID uuid) {
        return isSameChunk(playerMoveEvent.getOldPosition(), playerMoveEvent.getNewPosition())
                && hasPlayerLoaded(uuid, chunkX, chunkY);
    }

    protected void sendNewNearbyChunks(Player player, int chunkX, int chunkY, int initialCount) {
        final int sendDistance = player.getViewDistance() + 1;
        UUID uuid = player.getID().getUUID();

        Stream.Builder<Pair<Integer, Integer>> builder = Stream.builder();
        builder.accept(Pair.of(chunkX, chunkY));
        // generate positions around player in order of proximity
        for (int distance = 1; distance <= sendDistance * 1.5; distance++) {
            for (int relY = Math.max(0, distance - sendDistance); relY < Math.min(distance, sendDistance + 1); relY++) {
                int relX = distance - relY;

                builder.accept(Pair.of(chunkX + relX, chunkY + relY));
                builder.accept(Pair.of(chunkX + relY, chunkY - relX));
                builder.accept(Pair.of(chunkX - relX, chunkY - relY));
                builder.accept(Pair.of(chunkX - relY, chunkY + relX));
            }
        }

        builder.build()
                .filter(coord -> !hasPlayerLoaded(uuid, coord.getLeft(), coord.getRight()))
                .forEach(coords -> {
                    // stop sending if the player already moved further
                    if (updateCounterMap.get(uuid).get() != initialCount) {
                        return;
                    }

                    final int currentX = coords.getLeft();
                    final int currentY = coords.getRight();

                    playerLoad(uuid, currentX, currentY);
                    sendChunkLoad(player, currentX, currentY);
                });
    }

    protected void unloadRemoteChunks(Player player, int chunkX, int chunkY) {
        final int sendDistance = player.getViewDistance() + 1;
        UUID uuid = player.getID().getUUID();

        // copy to avoid ConcurrentModificationException on unload
        new ArrayList<>(playerHasLoaded.get(uuid)).stream()
                .filter(chunk -> !isWithinRange(chunkX, chunkY, chunk.getLeft(), chunk.getRight(), sendDistance))
                .filter(chunk -> hasPlayerLoaded(uuid, chunk.getLeft(), chunk.getRight()))
                .forEach(chunk -> {
                    playerUnload(uuid, chunk.getLeft(), chunk.getRight());
                    sendChunkUnload(player, chunk.getLeft(), chunk.getRight());
                });
    }

    protected boolean isWithinRange(int x1, int y1, int x2, int y2, int range) {
        return Math.abs(x2 - x1) <= range && Math.abs(y2 - y1) <= range;
    }

    protected void sendChunkUnload(Player player, int x, int y) {
        UnloadChunkPacket unloadChunkPacket = new UnloadChunkPacket(x, y);
        player.sendPacket(unloadChunkPacket);
    }

    protected void sendChunkLoad(Player player, int x, int y) {
        World world = player.getEntity().getWorld();

        world.getChunk(x, y).addCallback(chunk -> {
            if (chunk == null) {
                logger.error("Chunk {}:{} is null", x, y);
                return;
            }

            ChunkDataPacket chunkDataPacket = new ChunkDataPacket(x, y, true, chunk.getBlockDataStorage(), new NamedBinaryTag[]{});
            player.sendPacket(chunkDataPacket);
        }, throwable -> {
            logger.error("Error getting Chunk", throwable);
        });
    }

    @Async("playerExec")
    @EventListener
    public void onPlayerDisconnect(PlayerQuitEvent playerQuitEvent) {
        playerUnloadAll(playerQuitEvent.getPlayer().getID().getUUID());
    }

    private boolean isSameChunk(Position oldPosition, Position newPosition) {
        final int oldChunkX = oldPosition.getXAsInt() >> 4;
        final int oldChunkY = oldPosition.getZAsInt() >> 4;

        final int newChunkX = newPosition.getXAsInt() >> 4;
        final int newChunkY = newPosition.getZAsInt() >> 4;

        return oldChunkX == newChunkX && oldChunkY == newChunkY;
    }

    private void playerLoad(UUID uuid, int chunkX, int chunkY) {
        playerHasLoaded.get(uuid).add(Pair.of(chunkX, chunkY));
    }

    private void playerUnload(UUID uuid, int chunkX, int chunkY) {
        playerHasLoaded.get(uuid).remove(Pair.of(chunkX, chunkY));
    }

    private boolean hasPlayerLoaded(UUID uuid, int chunkX, int chunkY) {
        return playerHasLoaded.get(uuid).contains(Pair.of(chunkX, chunkY));
    }

    private void playerUnloadAll(UUID uuid) {
        // request stop of loading process
        updateCounterMap.computeIfAbsent(uuid, k -> new AtomicInteger(0)).incrementAndGet();
        synchronized (lockMap.computeIfAbsent(uuid, k -> new Object())) {
            playerHasLoaded.removeAll(uuid);
            updateCounterMap.remove(uuid);
            lockMap.remove(uuid);
        }
    }
}