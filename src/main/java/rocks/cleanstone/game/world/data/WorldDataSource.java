package rocks.cleanstone.game.world.data;

import javax.annotation.Nullable;

import rocks.cleanstone.game.world.chunk.Chunk;

public interface WorldDataSource {

    void close();

    @Nullable
    Chunk loadExistingChunk(int x, int y);

    void saveChunk(Chunk chunk);
}
