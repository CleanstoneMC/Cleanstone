package rocks.cleanstone.player.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.world.chunk.ChunkCoords;
import rocks.cleanstone.game.world.chunk.NearbyChunkRetriever;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerChunkLoadService;
import rocks.cleanstone.player.event.PlayerMoveEvent;
import rocks.cleanstone.player.event.PlayerQuitEvent;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class PlayerMoveChunkLoadListener {

    private final Map<UUID, AtomicInteger> updateCounterMap = new ConcurrentHashMap<>();
    private final PlayerChunkLoadService playerChunkLoadService;
    private final NearbyChunkRetriever nearbyChunkRetriever;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public PlayerMoveChunkLoadListener(PlayerChunkLoadService playerChunkLoadService, NearbyChunkRetriever nearbyChunkRetriever) {
        this.playerChunkLoadService = playerChunkLoadService;
        this.nearbyChunkRetriever = nearbyChunkRetriever;
    }

    @Async("playerExec")
    @EventListener
    public void onPlayerMove(PlayerMoveEvent playerMoveEvent) {
        final int chunkX = playerMoveEvent.getNewPosition().getXAsInt() >> 4;
        final int chunkZ = playerMoveEvent.getNewPosition().getZAsInt() >> 4;
        ChunkCoords coords = ChunkCoords.of(chunkX, chunkZ);

        final Player player = playerMoveEvent.getPlayer();
        UUID uuid = player.getID().getUUID();

        // reject unneeded updates early
        if (chunkUpdateNotNeeded(playerMoveEvent, coords, uuid)) {
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
            if (chunkUpdateNotNeeded(playerMoveEvent, coords, uuid)) {
                return;
            }

            logger.debug("loading chunks around {} for {}", coords, player.getID().getName());
            sendNewNearbyChunks(player, coords, loadingToken);
            unloadRemoteChunks(player, coords);
        }
    }

    @Async("playerExec")
    @EventListener
    public void onPlayerDisconnect(PlayerQuitEvent playerQuitEvent) {
        playerUnloadAll(playerQuitEvent.getPlayer().getID().getUUID());
    }

    protected boolean chunkUpdateNotNeeded(PlayerMoveEvent playerMoveEvent, ChunkCoords coords, UUID uuid) {
        return isSameChunk(playerMoveEvent.getOldPosition(), playerMoveEvent.getNewPosition())
                && playerChunkLoadService.hasPlayerLoaded(uuid, coords);
    }

    protected void sendNewNearbyChunks(Player player, ChunkCoords playerCoords, int loadingToken) {
        final int sendDistance = player.getViewDistance() + 1;
        UUID uuid = player.getID().getUUID();

        Collection<ChunkCoords> nearbyCoords = nearbyChunkRetriever.getChunkCoordsAround(
                playerCoords.getX(), playerCoords.getZ(), sendDistance);

        for (ChunkCoords coords : nearbyCoords) {
            if (shouldAbortLoading(uuid, loadingToken)) {
                logger.debug("aborted loading chunks for {}", player.getID().getName());
                return;
            }

            playerChunkLoadService.loadChunk(player, coords);
        }

        logger.debug("done loading chunks for {}", player.getID().getName());
    }


    protected void unloadRemoteChunks(Player player, ChunkCoords playerCoords) {
        final int sendDistance = player.getViewDistance() + 1;
        UUID uuid = player.getID().getUUID();

        playerChunkLoadService.getLoadedChunkCoords(uuid).stream()
                .filter(chunk -> !isWithinRange(playerCoords.getX(), playerCoords.getZ(), chunk.getX(), chunk.getZ(), sendDistance))
                .forEach(coords -> {
                    // use specific functions, because it is certain that the player has loaded these chunks
                    playerChunkLoadService.unregisterLoadedChunk(uuid, coords);
                    playerChunkLoadService.sendChunkUnloadPacket(player, coords);
                });
    }

    protected boolean isWithinRange(int x1, int z1, int x2, int z2, int range) {
        return Math.abs(x2 - x1) <= range && Math.abs(z2 - z1) <= range;
    }

    private boolean isSameChunk(Position oldPosition, Position newPosition) {
        final int oldChunkX = oldPosition.getXAsInt() >> 4;
        final int oldchunkZ = oldPosition.getZAsInt() >> 4;

        final int newChunkX = newPosition.getXAsInt() >> 4;
        final int newchunkZ = newPosition.getZAsInt() >> 4;

        return oldChunkX == newChunkX && oldchunkZ == newchunkZ;
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