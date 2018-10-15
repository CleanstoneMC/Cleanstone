package rocks.cleanstone.game.world.chunk;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.world.World;

@Slf4j
@Service
public class ProximityNearbyChunkRetriever implements NearbyChunkRetriever {

    @Override
    public Collection<ChunkCoords> getChunkCoordsAround(ChunkCoords coords, int radius) {
        final Collection<ChunkCoords> nearbyCoords = new ArrayList<>();
        final int chunkX = coords.getX();
        final int chunkZ = coords.getZ();
        nearbyCoords.add(coords);
        // generate positions around chunk in order of proximity
        for (int distance = 1; distance <= radius * 1.5; distance++) {
            for (int relZ = Math.max(0, distance - radius); relZ < Math.min(distance, radius + 1); relZ++) {
                final int relX = distance - relZ;
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
                            log.error("Failed to get chunk " + coords + " in world "
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
                .map(world::getLoadedChunk)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }
}
