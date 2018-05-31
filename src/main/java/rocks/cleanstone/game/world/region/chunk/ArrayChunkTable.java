package rocks.cleanstone.game.world.region.chunk;

import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.material.VanillaMaterial;

import java.util.Arrays;
import java.util.Collection;

public class ArrayChunkTable implements ChunkTable {

    private final Block[][][] blocks;

    public ArrayChunkTable(Block[][][] blocks) {
        this.blocks = blocks;
    }

    public ArrayChunkTable() {
        blocks = new Block[Chunk.WIDTH][Chunk.WIDTH][Chunk.HEIGHT];
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        Block block = blocks[x][z][y];
        return block != null ? block : ImmutableBlock.of(VanillaMaterial.AIR);
    }

    public void setBlock(int x, int y, int z, Block block) {
        if (block == null || block.getState().getMaterial() == VanillaMaterial.AIR) {
            blocks[x][z][y] = null;
        } else
            blocks[x][z][y] = block;
    }

    @Override
    public Collection<Block> getBlocks() {
        return Arrays.asList((Block[]) Arrays.stream(blocks).toArray());
    }
}
