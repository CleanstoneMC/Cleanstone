package rocks.cleanstone.game.world.chunk;

import com.google.common.base.Preconditions;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.world.data.WorldDataSource;
import rocks.cleanstone.game.world.generation.WorldGenerator;

/**
 * Loads/Generates a chunk that isnt already in local memory
 */
@Component
@Scope("prototype")
public class SimpleChunkProvider implements ChunkProvider {

    private final WorldDataSource dataSource;
    private final WorldGenerator generator;
    private int seed = 1234567890; //TODO: Move me

    public SimpleChunkProvider(WorldDataSource dataSource, WorldGenerator generator) {
        this.dataSource = dataSource;
        this.generator = generator;
    }

    @Async(value = "worldExec")
    @Override
    public ListenableFuture<Chunk> getChunk(int x, int y) {
        Chunk chunk = dataSource.loadExistingChunk(x, y);
        if (chunk == null) {
            chunk = generator.generateChunk(seed, x, y);
            chunk.setHasUnsavedChanges(true);
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
