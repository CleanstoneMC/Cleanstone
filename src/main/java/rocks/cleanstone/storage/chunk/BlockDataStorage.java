package rocks.cleanstone.storage.chunk;

import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.state.BlockState;

import java.util.Collection;

/**
 * Contains the current blocks (and similar data) of a Chunk
 */
public interface BlockDataStorage {
    Block getBlock(int x, int y, int z);

    void setBlock(int x, int y, int z, Block block);

    BlockState getBlockState(int x, int y, int z);

    void setBlockState(int x, int y, int z, BlockState state);

    byte getBlockLight(int x, int y, int z);

    void setBlockLight(int x, int y, int z, byte blockLight);

    byte getSkyLight(int x, int y, int z);

    void setSkyLight(int x, int y, int z, byte skyLight);

    boolean hasSkylight();

    /**
     * @return An immutable collection of all non-Air blocks
     */
    Collection<Block> getBlocks();

    BlockDataStorage clone();
}
