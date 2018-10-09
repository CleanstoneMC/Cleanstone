package rocks.cleanstone.game.entity;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import rocks.cleanstone.game.entity.vanilla.Human;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.ChunkCoords;
import rocks.cleanstone.game.world.chunk.NearbyChunkRetriever;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

@Service
public class NearbyEntityRetriever {

    private final PlayerManager playerManager;
    private final NearbyChunkRetriever nearbyChunkRetriever;

    public NearbyEntityRetriever(PlayerManager playerManager, NearbyChunkRetriever nearbyChunkRetriever) {
        this.playerManager = playerManager;
        this.nearbyChunkRetriever = nearbyChunkRetriever;
    }

    @Async
    public ListenableFuture<Set<Entity>> getEntitiesInRadius(Entity entity, int chunkRadius)
            throws ExecutionException, InterruptedException {
        World world = entity.getWorld();
        Chunk baseChunk = world.getChunkAt(entity.getPosition()).get();

        return new AsyncResult<>(nearbyChunkRetriever.getChunksAround(
                baseChunk.getCoordinates(), chunkRadius, world).get().stream()
                .flatMap(c -> c.getEntities().stream())
                .collect(Collectors.toSet()));
    }

    public Set<Entity> getLoadedEntitiesInRadius(Entity entity, int chunkRadius) {
        World world = entity.getWorld();
        return nearbyChunkRetriever.getLoadedChunksAround(ChunkCoords.of(entity.getPosition()), chunkRadius, world)
                .stream().flatMap(c -> c.getEntities().stream())
                .collect(Collectors.toSet());
    }

    @Async
    public ListenableFuture<Set<Player>> getPlayersInRadius(Entity baseEntity, int chunkRadius)
            throws ExecutionException, InterruptedException {
        return new AsyncResult<>(getEntitiesInRadius(baseEntity, chunkRadius).get().stream()
                .filter(e -> e instanceof Human)
                .map(e -> (Human) e)
                .map(playerManager::getOnlinePlayer)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet()));
    }

    public Set<Player> getLoadedPlayersInRadius(Entity baseEntity, int chunkRadius) {
        return getLoadedEntitiesInRadius(baseEntity, chunkRadius).stream()
                .filter(e -> e instanceof Human)
                .map(e -> (Human) e)
                .map(playerManager::getOnlinePlayer)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }
}
