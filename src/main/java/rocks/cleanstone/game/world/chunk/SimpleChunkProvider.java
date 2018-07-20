package rocks.cleanstone.game.world.chunk;

import com.google.common.base.Preconditions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import rocks.cleanstone.game.world.data.WorldDataSource;
import rocks.cleanstone.game.world.generation.WorldGenerator;

/**
 * Loads/Generates a chunk that isnt already in local memory
 */
public class SimpleChunkProvider implements ChunkProvider {

    private final WorldDataSource dataSource;
    private final WorldGenerator generator;
    private final AsyncListenableTaskExecutor executor;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public SimpleChunkProvider(WorldDataSource dataSource, WorldGenerator generator,
                               AsyncListenableTaskExecutor executor) {
        this.dataSource = dataSource;
        this.generator = generator;
        this.executor = executor;
    }

    @Override
    public ListenableFuture<Chunk> getChunk(int x, int y) {
        return executor.submitListenable(() -> {
            Chunk chunk = dataSource.loadExistingChunk(x, y);
            if (chunk == null) {
                chunk = generator.generateChunk(x, y);
                Preconditions.checkNotNull(chunk, "generated chunk cannot be null");
            }
            return chunk;
        });
    }

    @Override
    public WorldGenerator getGenerator() {
        return generator;
    }

    @Override
    public WorldDataSource getDataSource() {
        return dataSource;
    }
}
