package rocks.cleanstone.game.world.region;

import org.springframework.util.concurrent.ListenableFuture;

import java.util.Collection;

import rocks.cleanstone.game.world.chunk.Chunk;

public interface Region {

    int CHUNK_COUNT_ROOT = 32;

    int getX();

    int getY();

    Collection<Chunk> getLoadedChunks();

    boolean isChunkLoaded(int x, int y);

    Chunk getLoadedChunk(int x, int y);

    ListenableFuture<Chunk> getChunk(int x, int y);
}
