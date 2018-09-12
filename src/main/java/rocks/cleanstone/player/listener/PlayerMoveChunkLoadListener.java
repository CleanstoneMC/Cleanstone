package rocks.cleanstone.player.listener;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.world.chunk.ChunkService;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerChunkLoadService;
import rocks.cleanstone.player.event.PlayerMoveEvent;
import rocks.cleanstone.player.event.PlayerQuitEvent;

@Component
public class PlayerMoveChunkLoadListener {

    private final Map<UUID, AtomicInteger> updateCounterMap = new ConcurrentHashMap<>();
    private final PlayerChunkLoadService playerChunkLoadService;
    private final ChunkService chunkService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public PlayerMoveChunkLoadListener(PlayerChunkLoadService playerChunkLoadService, ChunkService chunkService) {
        this.playerChunkLoadService = playerChunkLoadService;
        this.chunkService = chunkService;
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

        List<Pair<Integer, Integer>> coords = chunkService.getChunkCoordsAround(chunkX, chunkY, sendDistance)
                .collect(Collectors.toList());

        for (Pair<Integer, Integer> coord : coords) {
            if (shouldAbortLoading(uuid, loadingToken)) {
                logger.debug("aborted loading chunks for {}", player.getID().getName());
                return;
            }

            playerChunkLoadService.loadChunk(player, coord.getLeft(), coord.getRight());
        }

        logger.debug("done loading chunks for {}", player.getID().getName());
    }


    protected void unloadRemoteChunks(Player player, int chunkX, int chunkY) {
        final int sendDistance = player.getViewDistance() + 1;
        UUID uuid = player.getID().getUUID();

        playerChunkLoadService.getLoadedChunks(uuid).stream()
                .filter(chunk -> !isWithinRange(chunkX, chunkY, chunk.getLeft(), chunk.getRight(), sendDistance))
                .forEach(chunk -> {
                    // use specific functions, because it is certain that the player has loaded these chunks
                    playerChunkLoadService.unregisterLoadedChunk(uuid, chunk.getLeft(), chunk.getRight());
                    playerChunkLoadService.sendChunkLoadPacket(player, chunk.getLeft(), chunk.getRight());
                });
    }

    protected boolean isWithinRange(int x1, int y1, int x2, int y2, int range) {
        return Math.abs(x2 - x1) <= range && Math.abs(y2 - y1) <= range;
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
            playerChunkLoadService.unregisterAllLoadedChunks(uuid);
            updateCounterMap.remove(uuid);
        }
    }
}