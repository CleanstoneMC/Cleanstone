package rocks.cleanstone.game.world.region;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.world.region.chunk.Chunk;
import rocks.cleanstone.game.world.region.chunk.SimpleChunk;
import rocks.cleanstone.net.minecraft.login.SessionServerResponse;

import java.util.Arrays;
import java.util.Collection;

public class SimpleRegion implements Region {

    private static final int CHUNK_COUNT_ROOT = 32;

    private final Chunk[][] chunks;
    private final RegionWorker regionWorker;

    public SimpleRegion(Chunk[][] chunks, RegionWorker regionWorker) {
        this.chunks = chunks;
        this.regionWorker = regionWorker;
    }

    public SimpleRegion(RegionWorker regionWorker) {
        this.regionWorker = regionWorker;
        chunks = new SimpleChunk[CHUNK_COUNT_ROOT][CHUNK_COUNT_ROOT];
    }

    @Override
    public Collection<Chunk> getLoadedChunks() {
        return Arrays.asList((Chunk[]) Arrays.stream(chunks).toArray());
    }


    public boolean isChunkLoaded(int x, int y) {
        return chunks[x][y] != null;
    }

    @Async("worldLoadingExec")
    @Override
    public ListenableFuture<Chunk> getChunk(int x, int y) {
        if (isChunkLoaded(x, y)) {
            return new AsyncResult<>(chunks[x][y]);
        }

        ListenableFuture<Chunk> chunkListenableFuture = regionWorker.loadChunk(x, y);
        chunkListenableFuture.addCallback(result -> {
            chunks[x][y] = result;
        }, ex -> {
            //TODO
        });

        return chunkListenableFuture;
    }
}
