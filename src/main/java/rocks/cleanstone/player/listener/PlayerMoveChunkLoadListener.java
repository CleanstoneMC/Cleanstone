package rocks.cleanstone.player.listener;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.UUID;

import rocks.cleanstone.core.config.MinecraftConfig;
import rocks.cleanstone.data.vanilla.nbt.NamedBinaryTag;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.net.packet.outbound.ChunkDataPacket;
import rocks.cleanstone.net.packet.outbound.UnloadChunkPacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.event.PlayerMoveEvent;
import rocks.cleanstone.player.event.PlayerQuitEvent;

public class PlayerMoveChunkLoadListener {

    private final Multimap<UUID, Pair<Integer, Integer>> playerHasLoaded = ArrayListMultimap.create();
    private final MinecraftConfig minecraftConfig;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public PlayerMoveChunkLoadListener(MinecraftConfig minecraftConfig) {
        this.minecraftConfig = minecraftConfig;
    }

    @Async("playerExec")
    @EventListener
    public void onPlayerMove(PlayerMoveEvent playerMoveEvent) {
        final int chunkX = ((int) playerMoveEvent.getNewPosition().getX()) >> 4;
        final int chunkY = ((int) playerMoveEvent.getNewPosition().getZ()) >> 4;

        final Player player = playerMoveEvent.getPlayer();
        UUID uuid = player.getId().getUUID();

        if (isSameChunk(playerMoveEvent.getOldPosition(), playerMoveEvent.getNewPosition())
                && hasPlayerLoaded(uuid, chunkX, chunkY)) {
            return;
        }
        sendNewNearbyChunks(player, chunkX, chunkY);
    }

    protected synchronized void sendNewNearbyChunks(Player player, int chunkX, int chunkY) {
        final int sendDistance = player.getViewDistance();
        final int checkDistance = sendDistance + 5;

        UUID uuid = player.getId().getUUID();
        Collection<Pair<Integer, Integer>> relCoordinates = new HashSet<>();
        for (int relX = -checkDistance; relX < checkDistance; relX++) {
            for (int relY = -checkDistance; relY < checkDistance; relY++) {
                relCoordinates.add(Pair.of(relX, relY));
            }
        }
        relCoordinates.stream()
                .sorted(Comparator.comparingInt(p -> Math.abs(p.getLeft()) + Math.abs(p.getRight())))
                .forEach(sortedCoordinates -> {
                    int relX = sortedCoordinates.getLeft(), relY = sortedCoordinates.getRight();
                    final int currentX = chunkX + relX;
                    final int currentY = chunkY + relY;

                    if (relX < -sendDistance || relX > sendDistance
                            || relY < -sendDistance || relY > sendDistance) {
                        if (hasPlayerLoaded(uuid, currentX, currentY)) {
                            playerUnload(uuid, currentX, currentY);
                            sendChunkUnload(player, currentX, currentY);
                        }
                    } else if (!hasPlayerLoaded(uuid, currentX, currentY)) {
                        playerLoad(uuid, currentX, currentY);
                        sendChunkLoad(player, currentX, currentY);
                    }
                });
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
        playerUnloadAll(playerQuitEvent.getPlayer().getId().getUUID());
    }

    private boolean isSameChunk(Position oldPosition, Position newPosition) {
        final int oldChunkX = ((int) oldPosition.getX()) >> 4;
        final int oldChunkY = ((int) oldPosition.getZ()) >> 4;

        final int newChunkX = ((int) newPosition.getX()) >> 4;
        final int newChunkY = ((int) newPosition.getZ()) >> 4;

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

    private synchronized void playerUnloadAll(UUID uuid) {
        playerHasLoaded.removeAll(uuid);
    }
}