package rocks.cleanstone.storage.engine.unsafe;

import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.storage.chunk.BlockDataStorage;
import sun.misc.Unsafe;

import java.util.Collection;

public class UnsafeBlockDataStorage implements BlockDataStorage {
    private final long position;
    private final int bitsPerBlock;

    private static final Unsafe UNSAFE = Unsafe.getUnsafe();

    public UnsafeBlockDataStorage() {
        int blockStates = 1000;

        bitsPerBlock = getBitsNeeded(blockStates);
        int neededBytes = Chunk.WIDTH * Chunk.WIDTH * Chunk.HEIGHT * bitsPerBlock / 8;

        position = UNSAFE.allocateMemory(neededBytes);
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        long data = UNSAFE.getAddress(position + bitsPerBlock * getBlockAddress(x, y, z));
        int blockOffset = (int) (data >> 64 - bitsPerBlock);

        return null; //TODO: Get correct Block for offset
    }

    @Override
    public void setBlock(int x, int y, int z, Block block) {
        int blockOffset = 10; //TODO: Get correct offset

        long entryOffset = position + bitsPerBlock * getBlockAddress(x, y, z);
        long data = UNSAFE.getAddress(entryOffset);

        data = ((0xFFFF >> 16 - bitsPerBlock) & data) | blockOffset <<  64 - bitsPerBlock;

        UNSAFE.putAddress(entryOffset, data);
    }

    @Override
    public BlockState getBlockState(int x, int y, int z) {
        return null;
    }

    @Override
    public void setBlockState(int x, int y, int z, BlockState state) {

    }

    @Override
    public byte getBlockLight(int x, int y, int z) {
        return 0;
    }

    @Override
    public void setBlockLight(int x, int y, int z, byte blockLight) {

    }

    @Override
    public byte getSkyLight(int x, int y, int z) {
        return 0;
    }

    @Override
    public void setSkyLight(int x, int y, int z, byte skyLight) {

    }

    @Override
    public boolean hasSkylight() {
        return false;
    }

    @Override
    public Collection<Block> getBlocks() {
        return null;
    }

    @Override
    public BlockDataStorage clone() {
        return null;
    }

    private short getBlockAddress(int x, int y, int z) {
        return (short) ((y & 0xf) << 8 | z << 4 | x);
    }

    private int getBitsNeeded(int blockStates) {
        return ((int) ((Math.log(blockStates) / Math.log(2)))) + 1;
    }
}
