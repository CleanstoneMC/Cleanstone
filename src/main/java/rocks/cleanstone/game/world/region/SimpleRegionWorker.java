package rocks.cleanstone.game.world.region;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.world.region.chunk.Chunk;

public class SimpleRegionWorker implements RegionWorker {

    @Async("worldLoadingExec")
    @Override
    public ListenableFuture<Chunk> loadChunk(int x, int y) {
        // TODO
        return null;
    }

    @Override
    public void unloadChunk(int x, int y) {
        // TODO
    }
}
