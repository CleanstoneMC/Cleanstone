package rocks.cleanstone.game.world.data;

import javax.annotation.Nullable;

import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.ChunkCoords;

public interface WorldDataSource {

    void close();

    @Nullable
    Chunk loadExistingChunk(ChunkCoords coords);

    void saveChunk(Chunk chunk);

    void drop();
}
