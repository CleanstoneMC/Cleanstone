package rocks.cleanstone.game.world.chunk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import rocks.cleanstone.game.world.World;

@Service
public class ProximityNearbyChunkRetriever implements NearbyChunkRetriever {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Collection<ChunkCoords> getChunkCoordsAround(ChunkCoords coords, int radius) {
        Collection<ChunkCoords> nearbyCoords = new ArrayList<>();
        int chunkX = coords.getX(), chunkZ = coords.getZ();
        nearbyCoords.add(coords);
        // generate positions around chunk in order of proximity
        for (int distance = 1; distance <= radius * 1.5; distance++) {
            for (int relZ = Math.max(0, distance - radius); relZ < Math.min(distance, radius + 1); relZ++) {
                int relX = distance - relZ;
                nearbyCoords.add(ChunkCoords.of(chunkX + relX, chunkZ + relZ));
                nearbyCoords.add(ChunkCoords.of(chunkX + relZ, chunkZ - relX));
                nearbyCoords.add(ChunkCoords.of(chunkX - relX, chunkZ - relZ));
                nearbyCoords.add(ChunkCoords.of(chunkX - relZ, chunkZ + relX));
            }
        }
        return nearbyCoords;
    }

    @Async
    @Override
    public ListenableFuture<Collection<Chunk>> getChunksAround(ChunkCoords startCoords, int radius, World world) {
        try {
            return new AsyncResult<>(getChunkCoordsAround(startCoords, radius).stream()
                    .map(coords -> {
                        try {
                            return world.getChunk(coords).get();
                        } catch (InterruptedException | ExecutionException e) {
                            logger.error("Failed to get chunk " + coords + " in world "
                                    + world.getWorldConfig().getName(), e);
                            throw new RuntimeException(e);
                        }
                    }).collect(Collectors.toSet()));
        } catch (Exception e) {
            return AsyncResult.forExecutionException(e);
        }
    }

    @Override
    public Collection<Chunk> getLoadedChunksAround(ChunkCoords startCoords, int radius, World world) {
        return getChunkCoordsAround(startCoords, radius).stream()
                .map(world::getLoadedChunk).collect(Collectors.toSet());
    }
}
