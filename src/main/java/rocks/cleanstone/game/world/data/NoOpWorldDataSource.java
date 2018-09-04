package rocks.cleanstone.game.world.data;

import javax.annotation.Nullable;
import rocks.cleanstone.game.world.chunk.Chunk;

public class NoOpWorldDataSource implements WorldDataSource {
    public NoOpWorldDataSource() {
    }

    @Nullable
    @Override
    public Chunk loadExistingChunk(int x, int y) {
        return null;
    }

    @Override
    public void saveChunk(Chunk chunk) {

    }

    @Override
    public void close() {
    }
}
