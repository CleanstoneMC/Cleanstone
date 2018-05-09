package rocks.cleanstone.game.world.region;

import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.world.region.chunk.Chunk;
import rocks.cleanstone.game.world.region.chunk.SimpleChunk;

import java.util.Arrays;
import java.util.Collection;

public class SimpleRegion implements Region {

    private static final int CHUNK_COUNT_ROOT = 32;

    private final Chunk[][] chunks;


    public SimpleRegion() {
        chunks = new SimpleChunk[CHUNK_COUNT_ROOT][CHUNK_COUNT_ROOT];
    }

    @Override
    public Collection<Chunk> getLoadedChunks() {
        return Arrays.asList((Chunk[]) Arrays.stream(chunks).toArray());
    }

    @Override
    public Chunk getLoadedChunk(int x, int y) {
        return chunks[x][y];
    }

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
