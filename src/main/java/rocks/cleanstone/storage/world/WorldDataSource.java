package rocks.cleanstone.storage.world;

import javax.annotation.Nullable;

import rocks.cleanstone.game.block.state.property.Property;
import rocks.cleanstone.game.material.block.BlockType;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.ChunkCoords;

public interface WorldDataSource {

    void close();

    @Nullable
    Chunk loadExistingChunk(ChunkCoords coords);

    void saveChunk(Chunk chunk);

    void drop();

    int getPropertyID(Property<?> property);

    Property<?> getProperty(int numericID);

    int getBlockTypeID(BlockType blockType);

    BlockType getBlockType(int numericID);
}
