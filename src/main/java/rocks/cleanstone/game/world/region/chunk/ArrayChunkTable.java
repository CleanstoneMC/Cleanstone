package rocks.cleanstone.game.world.region.chunk;

import org.springframework.lang.Nullable;
import rocks.cleanstone.game.world.region.block.Block;

import java.util.Arrays;
import java.util.Collection;

public class ArrayChunkTable implements ChunkTable {

    private Block[][][] blocks;

    public ArrayChunkTable(Block[][][] blocks) {
        this.blocks = blocks;
    }

    public ArrayChunkTable() {
        blocks = new Block[16][16][256];
    }

    @Override
    @Nullable
    public Block getBlock(int x, int y, int z) {
        return blocks[x][z][y];
    }

    public void setBlock(int x, int y, int z, Block block) {
        blocks[x][z][y] = block;
    }

    @Override
    public Collection<Block> getBlocks() {
        return Arrays.asList((Block[]) Arrays.stream(blocks).toArray());
    }
}
