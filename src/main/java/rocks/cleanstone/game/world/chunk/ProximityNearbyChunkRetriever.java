package rocks.cleanstone.game.world.chunk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.world.World;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class ProximityNearbyChunkRetriever implements NearbyChunkRetriever {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Collection<ChunkCoords> getChunkCoordsAround(int chunkX, int chunkZ, int radius) {
        Collection<ChunkCoords> coords = new ArrayList<>();
        coords.add(ChunkCoords.of(chunkX, chunkZ));

        // generate positions around chunk in order of proximity
        for (int distance = 1; distance <= radius * 1.5; distance++) {
            for (int relZ = Math.max(0, distance - radius); relZ < Math.min(distance, radius + 1); relZ++) {
                int relX = distance - relZ;
                coords.add(ChunkCoords.of(chunkX + relX, chunkZ + relZ));
                coords.add(ChunkCoords.of(chunkX + relZ, chunkZ - relX));
                coords.add(ChunkCoords.of(chunkX - relX, chunkZ - relZ));
                coords.add(ChunkCoords.of(chunkX - relZ, chunkZ + relX));
            }
        }
        return coords;
    }

    @Async
    @Override
    public ListenableFuture<Collection<Chunk>> getChunksAround(int chunkX, int chunkZ, int radius, World world) {
        try {
            return new AsyncResult<>(getChunkCoordsAround(chunkX, chunkZ, radius).stream()
                    .map(coord -> world.getChunk(coord.getX(), coord.getZ()))
                    .map(chunkFuture -> {
                        try {
                            return chunkFuture.get();
                        } catch (InterruptedException | ExecutionException e) {
                            logger.error("Failed to get chunk " + chunkX + ":" + chunkZ + " in world "
                                    + world.getWorldConfig().getName(), e);
                            throw new RuntimeException(e);
                        }
                    }).collect(Collectors.toSet()));
        } catch (Exception e) {
            return AsyncResult.forExecutionException(e);
        }
    }
}
