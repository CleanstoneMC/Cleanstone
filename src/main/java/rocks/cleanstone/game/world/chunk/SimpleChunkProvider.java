package rocks.cleanstone.game.world.chunk;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.world.data.WorldDataSource;
import rocks.cleanstone.game.world.generation.WorldGenerator;

/**
 * Loads/Generates a chunk that isnt already in local memory
 */
public class SimpleChunkProvider implements ChunkProvider {

    private final WorldDataSource dataSource;
    private final WorldGenerator generator;

    @Autowired
    public SimpleChunkProvider(WorldDataSource dataSource, WorldGenerator generator) {
        this.dataSource = dataSource;
        this.generator = generator;
    }

    @Async(value = "worldExec")
    @Override
    public ListenableFuture<Chunk> getChunk(int x, int y) {
        Chunk chunk = dataSource.loadExistingChunk(x, y);
        if (chunk == null) {
            chunk = generator.generateChunk(x, y);
            chunk.setDirty(true);
            Preconditions.checkNotNull(chunk, "generated chunk cannot be null");
        }
        return new AsyncResult<>(chunk);
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
