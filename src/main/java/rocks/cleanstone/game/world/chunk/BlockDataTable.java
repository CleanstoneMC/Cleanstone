package rocks.cleanstone.game.world.chunk;

import java.util.Collection;

import rocks.cleanstone.game.block.Block;

/**
 * Contains the current blocks (and similar data) of a Chunk
 */
public interface BlockDataTable {
    Block getBlock(int x, int y, int z);

    void setBlock(int x, int y, int z, Block block);

    byte getBlockLight(int x, int y, int z);

    void setBlockLight(int x, int y, int z, byte blockLight);

    byte getSkyLight(int x, int y, int z);

    void setSkyLight(int x, int y, int z, byte skyLight);

    boolean hasSkylight();

    /**
     * @return An immutable collection of all non-Air blocks
     */
    Collection<Block> getBlocks();
}
