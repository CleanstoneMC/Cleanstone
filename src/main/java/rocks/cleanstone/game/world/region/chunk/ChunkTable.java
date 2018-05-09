package rocks.cleanstone.game.world.region.chunk;

import org.springframework.lang.Nullable;
import rocks.cleanstone.game.world.region.block.Block;

import java.util.Collection;

public interface ChunkTable {
    @Nullable
    Block getBlock(int x, int y, int z);

    Collection<Block> getBlocks();

    void setBlock(int x, int y, int z, Block block);
}
