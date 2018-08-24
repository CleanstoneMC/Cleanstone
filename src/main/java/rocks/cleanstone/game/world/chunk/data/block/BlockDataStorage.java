package rocks.cleanstone.game.world.chunk.data.block;

import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.world.chunk.BlockDataTable;

/**
 * Stores data about blocks (e.g. block states, block light, etc.) that can be converted into a byte stream or
 * {@link BlockDataTable} efficiently
 */
public interface BlockDataStorage {
    void setBlockState(int x, int y, int z, BlockState state);

    void setBlockLight(int x, int y, int z, byte blockLight);

    void setSkyLight(int x, int y, int z, byte skyLight);

    BlockDataTable constructTable();
}
