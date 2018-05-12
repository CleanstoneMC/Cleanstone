package rocks.cleanstone.game.world.region.chunk;

import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Collection;

import rocks.cleanstone.game.block.Block;

public class ArrayChunkTable implements ChunkTable {

    private Block[][][] blocks;

    public ArrayChunkTable(Block[][][] blocks) {
        this.blocks = blocks;
    }

    public ArrayChunkTable() {
        blocks = new Block[Chunk.WIDTH][Chunk.WIDTH][Chunk.HEIGHT];
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
