package rocks.cleanstone.game.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.entity.vanilla.Human;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.ChunkService;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

@Service
public class EntityTrackingService {

    private final PlayerManager playerManager;
    private final ChunkService chunkService;

    public EntityTrackingService(PlayerManager playerManager, ChunkService chunkService) {
        this.playerManager = playerManager;
        this.chunkService = chunkService;
    }

    @Async
    public ListenableFuture<Set<Entity>> getEntitiesInRadius(Entity entity, int chunkRadius) throws ExecutionException, InterruptedException {
        World world = entity.getWorld();

        Chunk baseChunk = world.getChunkAt(entity.getPosition()).get();
        int baseChunkX = baseChunk.getX();
        int baseChunkY = baseChunk.getY();

        Set<Entity> entities = chunkService.getChunksAround(baseChunkX, baseChunkY, chunkRadius, world)
                .flatMap(c -> c.getEntities().stream())
                .collect(Collectors.toSet());

        return new AsyncResult<>(entities);
    }

    @Async
    public ListenableFuture<Set<Player>> getPlayerInRadius(Entity baseEntity, int chunkRadius) throws ExecutionException, InterruptedException {
        Set<Entity> entities = getEntitiesInRadius(baseEntity, chunkRadius).get();

        Set<Player> players = new HashSet<>();
        for (Entity entity : entities) {
            if (!(entity instanceof Human)) {
                continue;
            }

            Player onlinePlayer = playerManager.getOnlinePlayer(entity);

            if (onlinePlayer != null) {
                players.add(onlinePlayer);
            }
        }

        return new AsyncResult<>(players);
    }
}
