package rocks.cleanstone.game.world.region.chunk;

import org.springframework.lang.Nullable;

import java.util.Collection;

import rocks.cleanstone.game.block.Block;

public interface ChunkTable {
    @Nullable
    Block getBlock(int x, int y, int z);

    Collection<Block> getBlocks();

    void setBlock(int x, int y, int z, Block block);
}
