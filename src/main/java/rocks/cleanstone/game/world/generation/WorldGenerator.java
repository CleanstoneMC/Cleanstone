package rocks.cleanstone.game.world.generation;

import rocks.cleanstone.game.world.chunk.Chunk;

public interface WorldGenerator {

    Chunk generateChunk(int seed, int x, int y);

    int getHeightAt(int seed, int x, int y);

    String getName();
}
