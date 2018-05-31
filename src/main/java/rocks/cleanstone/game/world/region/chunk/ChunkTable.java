package rocks.cleanstone.game.world.region.chunk;

import rocks.cleanstone.game.block.Block;

import java.util.Collection;

public interface ChunkTable {
    Block getBlock(int x, int y, int z);

    /**
     * @return An immutable collection of all non-Air blocks contained within this Chunk
     */
    Collection<Block> getBlocks();

    void setBlock(int x, int y, int z, Block block);
}
