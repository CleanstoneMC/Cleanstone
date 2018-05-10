package rocks.cleanstone.game.world.region;

import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.world.region.chunk.Chunk;

import java.util.Collection;

public interface Region {
    Collection<Chunk> getLoadedChunks();

    public boolean isChunkLoaded(int x, int y);

    public ListenableFuture<Chunk> getChunk(int x, int y);
}
