package rocks.cleanstone.game.world.data;

import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.ChunkCoords;

import javax.annotation.Nullable;

public class NoOpWorldDataSource implements WorldDataSource {
    public NoOpWorldDataSource() {
    }

    @Nullable
    @Override
    public Chunk loadExistingChunk(ChunkCoords coords) {
        return null;
    }

    @Override
    public void saveChunk(Chunk chunk) {

    }

    @Override
    public void close() {
    }

    @Override
    public void drop() {

    }
}
