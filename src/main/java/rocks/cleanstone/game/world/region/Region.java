package rocks.cleanstone.game.world.region;

import java.util.Collection;
import java.util.concurrent.Future;

import rocks.cleanstone.game.world.region.chunk.Chunk;

public interface Region {
    Collection<Chunk> getLoadedChunks();

    Future<Chunk> loadChunk(int x, int y);

    Chunk getChunk(int x, int y);
}
