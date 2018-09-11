package rocks.cleanstone.game.entity;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@Service
public class EntityTrackingService {

    private final PlayerManager playerManager;

    public EntityTrackingService(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Async
    public ListenableFuture<Set<Entity>> getEntitiesInRadius(Entity entity, int chunkRadius) throws ExecutionException, InterruptedException {
        World world = entity.getWorld();

        Chunk baseChunk = world.getChunkAt(entity.getPosition()).get();
        int baseChunkX = baseChunk.getX();
        int baseChunkY = baseChunk.getY();

        int chunkRadiusPositiveHalf = chunkRadius / 2;

        Chunk[][] chunks = new Chunk[chunkRadius][chunkRadius];

        for (int x = 0; x < chunkRadius; x++) {
            for (int y = 0; y < chunkRadius; y++) {
                chunks[x][y] = world.getChunk(baseChunkX + x - chunkRadiusPositiveHalf, baseChunkY + y - chunkRadiusPositiveHalf).get();
            }
        }

        Set<Entity> entities = new HashSet<>();
        Arrays.stream(chunks).forEach(xChunks -> Arrays.stream(xChunks).forEach(chunk -> entities.addAll(chunk.getEntities())));

        return new AsyncResult<>(entities);
    }

    @Async
    public ListenableFuture<Set<Player>> getPlayerInRadius(Entity baseEntity, int chunkRadius) throws ExecutionException, InterruptedException {
        Set<Entity> entities = getEntitiesInRadius(baseEntity, chunkRadius).get();

        Set<Player> players = new HashSet<>();
        for (Entity entity : entities) {
            Player onlinePlayer = playerManager.getOnlinePlayer(entity);

            if (onlinePlayer != null) {
                players.add(onlinePlayer);
            }
        }

        return new AsyncResult<>(players);
    }
}
