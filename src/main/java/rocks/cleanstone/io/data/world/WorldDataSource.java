package rocks.cleanstone.io.data.world;

import java.io.IOException;

import javax.annotation.Nullable;

import rocks.cleanstone.game.world.region.chunk.Chunk;

public interface WorldDataSource {
    void open() throws IOException;

    void close() throws IOException;

    @Nullable
    Chunk loadExistingChunk(int x, int y);

    void saveChunk(Chunk chunk);
}
