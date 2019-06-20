package rocks.cleanstone.storage.world;

import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.ChunkCoords;

import javax.annotation.Nullable;

public interface WorldDataSource {

    void close();

    @Nullable
    Chunk loadExistingChunk(ChunkCoords coords);

    void saveChunk(Chunk chunk);

    void drop();
}
