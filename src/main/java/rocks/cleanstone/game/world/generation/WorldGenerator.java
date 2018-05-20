package rocks.cleanstone.game.world.generation;

import rocks.cleanstone.game.world.region.chunk.Chunk;

public interface WorldGenerator {

    Chunk generateChunk(int x, int y);
}
