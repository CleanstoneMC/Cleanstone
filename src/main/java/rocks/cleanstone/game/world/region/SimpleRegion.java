package rocks.cleanstone.game.world.region;

import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.world.region.chunk.Chunk;

import java.util.Arrays;
import java.util.Collection;

public class SimpleRegion implements Region {

    private final Chunk[][] chunks;
    private final int x;
    private final int z;

    public SimpleRegion(Chunk[][] chunks, int x, int z) {
        this.chunks = chunks;
        this.x = x;
        this.z = z;
    }

    public SimpleRegion(int x, int z) {
        chunks = new Chunk[32][32];
        this.x = x;
        this.z = z;
    }

    @Override
    public Collection<Chunk> getLoadedChunks() {
        return Arrays.asList((Chunk[]) Arrays.stream(chunks).toArray());
    }

    @Override
    public Chunk getLoadedChunk(int x, int z) {
        return chunks[x][z];
    }

    @Override
    public ListenableFuture<Chunk> loadChunk(int x, int z) {
        return null;
    }

    @Override
    public void unloadChunk(int x, int z) {
        
    }
}
