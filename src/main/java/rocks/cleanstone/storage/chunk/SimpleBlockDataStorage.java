package rocks.cleanstone.storage.chunk;

import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.material.block.vanilla.VanillaBlockType;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.utils.NibbleArray;

import java.util.Arrays;
import java.util.Collection;

public class SimpleBlockDataStorage implements BlockDataStorage {

    private static final BlockState AIR = BlockState.of(VanillaBlockType.AIR);

    private final BlockState[][][] blocks = new BlockState[Chunk.WIDTH][Chunk.WIDTH][Chunk.HEIGHT];
    private final NibbleArray blockLightNibbleArray;
    private final NibbleArray skyLightNibbleArray;
    private final boolean hasSkyLight;

    public SimpleBlockDataStorage(SimpleBlockDataStorage simpleBlockDataStorage) {
        BlockState[][][] srcBlocks = simpleBlockDataStorage.blocks;
        for (int x = 0; x < srcBlocks.length; x++) {
            for (int z = 0; z < srcBlocks[x].length; z++) {
                System.arraycopy(srcBlocks[x][z], 0, blocks[x][z], 0, srcBlocks[x][z].length);
            }
        }

        this.blockLightNibbleArray = simpleBlockDataStorage.blockLightNibbleArray.snapshot();
        this.skyLightNibbleArray = simpleBlockDataStorage.skyLightNibbleArray.snapshot();
        this.hasSkyLight = simpleBlockDataStorage.hasSkyLight;
    }

    public SimpleBlockDataStorage(boolean hasSkyLight) {
        for (int x = 0; x < blocks.length; x++) {
            for (int z = 0; z < blocks[x].length; z++) {
                Arrays.fill(blocks[x][z], AIR);
            }
        }

        blockLightNibbleArray = new NibbleArray(Chunk.WIDTH * Chunk.WIDTH * Chunk.HEIGHT / 2);
        skyLightNibbleArray = new NibbleArray(Chunk.WIDTH * Chunk.WIDTH * Chunk.HEIGHT / 2);
        this.hasSkyLight = hasSkyLight;
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        return ImmutableBlock.of(blocks[x][z][y]);
    }

    @Override
    public void setBlock(int x, int y, int z, Block block) {
        blocks[x][z][y] = block.getState();
    }

    @Override
    public BlockState getBlockState(int x, int y, int z) {
        return blocks[x][z][y];
    }

    @Override
    public void setBlockState(int x, int y, int z, BlockState state) {
        blocks[x][z][y] = state;
    }

    @Override
    public byte getBlockLight(int x, int y, int z) {
        return blockLightNibbleArray.get(getBlockAddress(x, y, z));
    }

    @Override
    public void setBlockLight(int x, int y, int z, byte blockLight) {
         blockLightNibbleArray.set(getBlockAddress(x, y, z), blockLight);
    }

    @Override
    public byte getSkyLight(int x, int y, int z) {
         return skyLightNibbleArray.get(getBlockAddress(x, y, z));
    }

    @Override
    public void setSkyLight(int x, int y, int z, byte skyLight) {
        skyLightNibbleArray.set(getBlockAddress(x, y, z), skyLight);
    }

    @Override
    public boolean hasSkylight() {
        return hasSkyLight;
    }

    @Override
    public Collection<Block> getBlocks() {
        return null;
    }

    @Override
    public BlockDataStorage clone() {
        return new SimpleBlockDataStorage(this);
    }

    private int getBlockAddress(int x, int y, int z) {
        return (y & 0xf) << 8 | z << 4 | x;
    }
}
