package rocks.cleanstone.storage.engine.noop;

import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.ChunkCoords;
import rocks.cleanstone.storage.world.WorldDataSource;

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
