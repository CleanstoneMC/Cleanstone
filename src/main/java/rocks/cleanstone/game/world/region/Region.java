package rocks.cleanstone.game.world.region;

import org.springframework.util.concurrent.ListenableFuture;

import java.util.Collection;

import javax.annotation.Nullable;

import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.ChunkCoords;

/**
 * An independent area in the world that contains workers to do all the computational work (gravity, path
 * finding, redstone, etc) inside of its loaded chunks
 */
public interface Region {

    Collection<Chunk> getLoadedChunks();

    boolean isChunkLoaded(ChunkCoords coords);

    @Nullable
    Chunk getLoadedChunk(ChunkCoords coords);

    ListenableFuture<Chunk> getChunk(ChunkCoords coords);

    ListenableFuture<Chunk> loadChunk(ChunkCoords coords);

    void unloadChunk(ChunkCoords coords);

    void unloadAllChunks();

    String getID();
}
