package rocks.cleanstone.game.world.chunk;

import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import rocks.cleanstone.game.world.World;

@Service
public class ChunkService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public Stream<Pair<Integer, Integer>> getChunkCoordsAround(int chunkX, int chunkZ, int radius) {
        Stream.Builder<Pair<Integer, Integer>> builder = Stream.builder();

        builder.accept(Pair.of(chunkX, chunkZ));

        // generate positions around player in order of proximity
        for (int distance = 1; distance <= radius * 1.5; distance++) {
            for (int relZ = Math.max(0, distance - radius); relZ < Math.min(distance, radius + 1); relZ++) {
                int relX = distance - relZ;

                builder.accept(Pair.of(chunkX + relX, chunkZ + relZ));
                builder.accept(Pair.of(chunkX + relZ, chunkZ - relX));
                builder.accept(Pair.of(chunkX - relX, chunkZ - relZ));
                builder.accept(Pair.of(chunkX - relZ, chunkZ + relX));
            }
        }

        return builder.build();
    }

    public Stream<Chunk> getChunksAround(int chunkX, int chunkZ, int radius, World world) {
        return getChunkCoordsAround(chunkX, chunkZ, radius)
                .map(coord -> world.getChunk(coord.getLeft(), coord.getRight()))
                .map(chunkFuture -> {
                    try {
                        return chunkFuture.get();
                    } catch (InterruptedException | ExecutionException e) {
                        logger.error("Failed to get chunk " + chunkX + ":" + chunkZ + " in world " + world.getWorldConfig().getName(), e);
                        return null;
                    }
                })
                .filter(Objects::nonNull);
    }
}
