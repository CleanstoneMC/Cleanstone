package rocks.cleanstone.game.world.region;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.concurrent.ListenableFuture;

import rocks.cleanstone.game.world.data.WorldDataSource;
import rocks.cleanstone.game.world.generation.WorldGenerator;
import rocks.cleanstone.game.world.region.chunk.Chunk;

public class LocalRegionWorker implements RegionWorker {

    private final WorldDataSource dataSource;
    private final WorldGenerator generator;

    public LocalRegionWorker(WorldDataSource dataSource, WorldGenerator generator) {
        this.dataSource = dataSource;
        this.generator = generator;
    }

    @Async("worldLoadingExec")
    @Override
    public ListenableFuture<Chunk> loadChunk(int x, int y) {
        Chunk chunk = dataSource.loadExistingChunk(x, y);
        if (chunk == null) {
            chunk = generator.generateChunk(x, y);
        }
        return new AsyncResult<>(chunk);
    }

    @Override
    public void unloadChunk(int x, int y) {
        // TODO
    }
}
