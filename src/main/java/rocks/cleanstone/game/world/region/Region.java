package rocks.cleanstone.game.world.region;

import org.springframework.util.concurrent.ListenableFuture;

import java.util.Collection;

import rocks.cleanstone.game.world.chunk.Chunk;

/**
 * An independent area in the world that contains workers to do all the computational work (gravity, path
 * finding, redstone, etc) inside of its loaded chunks
 */
public interface Region {

    Collection<Chunk> getLoadedChunks();

    boolean isChunkLoaded(int chunkX, int chunkY);

    Chunk getLoadedChunk(int chunkX, int chunkY);

    ListenableFuture<Chunk> getChunk(int chunkX, int chunkY);

    ListenableFuture<Chunk> loadChunk(int chunkX, int chunkY);

    void unloadChunk(int chunkX, int chunkY);

    void unloadAllChunks();

    String getID();
}
