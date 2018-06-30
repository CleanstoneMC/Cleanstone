package rocks.cleanstone.game.world.generation;

import rocks.cleanstone.game.world.chunk.Chunk;

public interface WorldGenerator {

    Chunk generateChunk(int x, int y);
}
