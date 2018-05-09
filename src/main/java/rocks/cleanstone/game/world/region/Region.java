package rocks.cleanstone.game.world.region;

import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.world.region.chunk.Chunk;

import java.util.Collection;

public interface Region {
    Collection<Chunk> getLoadedChunks();

    Chunk getLoadedChunk(int x, int z);

    ListenableFuture<Chunk> loadChunk(int x, int z);

    void unloadChunk(int x, int z);
}
