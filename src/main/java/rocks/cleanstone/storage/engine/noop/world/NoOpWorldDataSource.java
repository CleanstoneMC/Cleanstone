package rocks.cleanstone.storage.engine.noop.world;

import javax.annotation.Nullable;

import rocks.cleanstone.game.block.state.property.Property;
import rocks.cleanstone.game.material.block.BlockType;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.ChunkCoords;
import rocks.cleanstone.storage.world.WorldDataSource;

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

    @Override
    public int getPropertyID(Property<?> property) {
        return 0;
    }

    @Override
    public Property<?> getProperty(int numericID) {
        return null;
    }

    @Override
    public int getBlockTypeID(BlockType blockType) {
        return 0;
    }

    @Override
    public BlockType getBlockType(int numericID) {
        return null;
    }
}
