package rocks.cleanstone.game.world.region;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.world.region.chunk.Chunk;
import rocks.cleanstone.game.world.region.chunk.SimpleChunk;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SimpleRegion implements Region {

    private static final int CHUNK_COUNT_ROOT = 32;

    private final Chunk[][] chunks;
    private final List<RegionWorker> regionWorkers;
    private int nextWorker = 0;

    public SimpleRegion(Chunk[][] chunks, Collection<RegionWorker> regionWorkers) {
        this.chunks = chunks;
        this.regionWorkers = new ArrayList<>(regionWorkers);
    }

    public SimpleRegion(Collection<RegionWorker> regionWorkers) {
        this(new SimpleChunk[CHUNK_COUNT_ROOT][CHUNK_COUNT_ROOT], regionWorkers);
    }

    @Override
    public Collection<Chunk> getLoadedChunks() {
        return Arrays.asList((Chunk[]) Arrays.stream(chunks).toArray());
    }

    @Override
    public boolean isChunkLoaded(int x, int y) {
        return chunks[x][y] != null;
    }

    @Nullable
    @Override
    public Chunk getLoadedChunk(int x, int y) {
        return chunks[x][y];
    }

    @Async("worldLoadingExec")
    @Override
    public ListenableFuture<Chunk> getChunk(int x, int y) {
        if (isChunkLoaded(x, y)) {
            return new AsyncResult<>(getLoadedChunk(x, y));
        }

        ListenableFuture<Chunk> chunkListenableFuture = getNextWorker().loadChunk(x, y);
        chunkListenableFuture.addCallback(result -> chunks[x][y] = result, ex -> {
            //TODO
        });

        return chunkListenableFuture;
    }

    private RegionWorker getNextWorker() {
        int next = nextWorker;
        if (next >= regionWorkers.size()) next = 0;
        nextWorker++;
        return regionWorkers.get(next);
    }

    @Override
    public Collection<RegionWorker> getWorkers() {
        return regionWorkers;
    }
}
