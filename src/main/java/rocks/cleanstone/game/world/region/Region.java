package rocks.cleanstone.game.world.region;

import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.world.region.chunk.Chunk;

import java.util.Collection;

public interface Region {
    Collection<Chunk> getLoadedChunks();

    Chunk getLoadedChunk(int x, int y);

    ListenableFuture<Chunk> loadChunk(int x, int y);

    void unloadChunk(int x, int y);
}
