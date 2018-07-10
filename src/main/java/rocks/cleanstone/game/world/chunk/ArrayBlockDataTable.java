package rocks.cleanstone.game.world.chunk;

import com.google.common.base.Objects;

import java.util.Arrays;
import java.util.Collection;

import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.material.block.vanilla.VanillaBlockType;

public class ArrayBlockDataTable implements BlockDataTable {

    private final Block[][][] blocks;
    private final byte[][][] blockLight, skyLight;
    private final boolean hasSkylight;

    public ArrayBlockDataTable(ArrayBlockDataTable arrayBlockDataTable) {
        this.blockLight = arrayBlockDataTable.blockLight.clone();
        this.skyLight = arrayBlockDataTable.skyLight.clone();
        this.hasSkylight = arrayBlockDataTable.hasSkylight;
        this.blocks = arrayBlockDataTable.blocks.clone();
    }

    public ArrayBlockDataTable(Block[][][] blocks, byte[][][] blockLight, byte[][][] skyLight,
                               boolean hasSkyLight) {
        this.blocks = blocks;
        this.blockLight = blockLight;
        this.skyLight = skyLight;
        this.hasSkylight = hasSkyLight;
    }

    public ArrayBlockDataTable(boolean hasSkyLight) {
        blocks = new Block[Chunk.WIDTH][Chunk.WIDTH][Chunk.HEIGHT];
        blockLight = new byte[Chunk.WIDTH][Chunk.WIDTH][Chunk.HEIGHT];
        skyLight = new byte[Chunk.WIDTH][Chunk.WIDTH][Chunk.HEIGHT];
        this.hasSkylight = hasSkyLight;
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        Block block = blocks[x][z][y];
        return block != null ? block : ImmutableBlock.of(VanillaBlockType.AIR);
    }

    public void setBlock(int x, int y, int z, Block block) {
        if (block == null || block.getState().getBlockType() == VanillaBlockType.AIR) {
            blocks[x][z][y] = null;
        } else
            blocks[x][z][y] = block;
    }

    @Override
    public byte getBlockLight(int x, int y, int z) {
        return blockLight[x][z][y];
    }

    @Override
    public void setBlockLight(int x, int y, int z, byte blockLight) {
        this.blockLight[x][z][y] = blockLight;
    }

    @Override
    public byte getSkyLight(int x, int y, int z) {
        return skyLight[x][z][y];
    }

    @Override
    public void setSkyLight(int x, int y, int z, byte skyLight) {
        this.skyLight[x][z][y] = skyLight;
    }

    @Override
    public boolean hasSkylight() {
        return hasSkylight;
    }

    @Override
    public Collection<Block> getBlocks() {
        return Arrays.asList((Block[]) Arrays.stream(blocks).toArray());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrayBlockDataTable)) return false;
        ArrayBlockDataTable that = (ArrayBlockDataTable) o;
        return hasSkylight == that.hasSkylight &&
                Arrays.deepEquals(blocks, that.blocks) &&
                Arrays.deepEquals(blockLight, that.blockLight) &&
                Arrays.deepEquals(skyLight, that.skyLight);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(blocks, blockLight, skyLight, hasSkylight);
    }
}
