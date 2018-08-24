package rocks.cleanstone.player.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.data.vanilla.nbt.NamedBinaryTag;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.net.packet.outbound.ChunkDataPacket;
import rocks.cleanstone.net.packet.outbound.UnloadChunkPacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerChunkLoadService;
import rocks.cleanstone.player.event.PlayerMoveEvent;
import rocks.cleanstone.player.event.PlayerQuitEvent;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class PlayerMoveChunkLoadListener {

    private final Map<UUID, AtomicInteger> updateCounterMap = new ConcurrentHashMap<>();
    private final PlayerChunkLoadService playerChunkLoadService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public PlayerMoveChunkLoadListener(PlayerChunkLoadService playerChunkLoadService) {
        this.playerChunkLoadService = playerChunkLoadService;
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

        int loadingToken = acquireLoadingToken(uuid);
        //noinspection SynchronizationOnLocalVariableOrMethodParameter
        synchronized (uuid) {
            // return early
            if (shouldAbortLoading(uuid, loadingToken)) {
                return;
            }
            // check again because the chunk could already have been loaded inside synchronized block
            if (chunkUpdateNotNeeded(playerMoveEvent, chunkX, chunkY, uuid)) {
                return;
            }

            logger.debug("loading chunks around {}, {} for {}", chunkX, chunkY, player.getID().getName());
            sendNewNearbyChunks(player, chunkX, chunkY, loadingToken);
            unloadRemoteChunks(player, chunkX, chunkY);
        }
    }

    @Async("playerExec")
    @EventListener
    public void onPlayerDisconnect(PlayerQuitEvent playerQuitEvent) {
        playerUnloadAll(playerQuitEvent.getPlayer().getID().getUUID());
    }

    protected boolean chunkUpdateNotNeeded(PlayerMoveEvent playerMoveEvent, int chunkX, int chunkY, UUID uuid) {
        return isSameChunk(playerMoveEvent.getOldPosition(), playerMoveEvent.getNewPosition())
                && playerChunkLoadService.hasPlayerLoaded(uuid, chunkX, chunkY);
    }

    protected void sendNewNearbyChunks(Player player, int chunkX, int chunkY, int loadingToken) {
        final int sendDistance = player.getViewDistance() + 1;
        UUID uuid = player.getID().getUUID();

        optionallyLoadChunk(player, uuid, chunkX, chunkY);
        // generate positions around player in order of proximity
        for (int distance = 1; distance <= sendDistance * 1.5; distance++) {
            for (int relY = Math.max(0, distance - sendDistance); relY < Math.min(distance, sendDistance + 1); relY++) {
                int relX = distance - relY;

                if (shouldAbortLoading(uuid, loadingToken)) {
                    logger.debug("aborted loading chunks for {}", player.getID().getName());
                    return;
                }

                optionallyLoadChunk(player, uuid, chunkX + relX, chunkY + relY);
                optionallyLoadChunk(player, uuid, chunkX + relY, chunkY - relX);
                optionallyLoadChunk(player, uuid, chunkX - relX, chunkY - relY);
                optionallyLoadChunk(player, uuid, chunkX - relY, chunkY + relX);
            }
        }

        logger.debug("done loading chunks for {}", player.getID().getName());
    }

    protected void optionallyLoadChunk(Player player, UUID uuid, int currentX, int currentY) {
        if (playerChunkLoadService.hasPlayerLoaded(uuid, currentX, currentY))
            return;

        playerChunkLoadService.playerLoad(uuid, currentX, currentY);
        sendChunkLoad(player, currentX, currentY);
    }

    protected void unloadRemoteChunks(Player player, int chunkX, int chunkY) {
        final int sendDistance = player.getViewDistance() + 1;
        UUID uuid = player.getID().getUUID();

        playerChunkLoadService.loadedChunks(uuid).stream()
                .filter(chunk -> !isWithinRange(chunkX, chunkY, chunk.getLeft(), chunk.getRight(), sendDistance))
                .forEach(chunk -> {
                    playerChunkLoadService.playerUnload(uuid, chunk.getLeft(), chunk.getRight());
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

            ChunkDataPacket chunkDataPacket = new ChunkDataPacket(x, y, true, chunk.getBlockDataTable(), new NamedBinaryTag[]{});
            player.sendPacket(chunkDataPacket);
        }, throwable -> {
            logger.error("Error getting Chunk", throwable);
        });
    }

    private boolean isSameChunk(Position oldPosition, Position newPosition) {
        final int oldChunkX = oldPosition.getXAsInt() >> 4;
        final int oldChunkY = oldPosition.getZAsInt() >> 4;

        final int newChunkX = newPosition.getXAsInt() >> 4;
        final int newChunkY = newPosition.getZAsInt() >> 4;

        return oldChunkX == newChunkX && oldChunkY == newChunkY;
    }

    protected int acquireLoadingToken(UUID uuid) {
        return updateCounterMap.computeIfAbsent(uuid, k -> new AtomicInteger(0)).incrementAndGet();
    }

    protected boolean shouldAbortLoading(UUID uuid, int loadingToken) {
        return !updateCounterMap.containsKey(uuid) || updateCounterMap.get(uuid).get() != loadingToken;
    }

    protected void playerUnloadAll(UUID uuid) {
        //noinspection SynchronizationOnLocalVariableOrMethodParameter
        synchronized (uuid) {
            playerChunkLoadService.unloadAll(uuid);
            updateCounterMap.remove(uuid);
        }
    }
}