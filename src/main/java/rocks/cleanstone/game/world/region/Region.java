package rocks.cleanstone.game.world.region;

import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.game.world.chunk.Chunk;

import java.util.Collection;

/**
 * An independent area in the world that contains workers to do all the computational work (gravity, path
 * finding, redstone, etc) inside of its loaded chunks
 */
public interface Region {

    Collection<Chunk> getLoadedChunks();

    boolean isChunkLoaded(int chunkX, int chunkZ);

    Chunk getLoadedChunk(int chunkX, int chunkZ);

    ListenableFuture<Chunk> getChunk(int chunkX, int chunkZ);

    ListenableFuture<Chunk> loadChunk(int chunkX, int chunkZ);

    void unloadChunk(int chunkX, int chunkZ);

    void unloadAllChunks();

    String getID();
}
