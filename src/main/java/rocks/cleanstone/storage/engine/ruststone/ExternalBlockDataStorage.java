package rocks.cleanstone.storage.engine.ruststone;

import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.storage.chunk.BlockDataStorage;

import java.util.Collection;

public class ExternalBlockDataStorage implements BlockDataStorage {
    @Override
    public native Block getBlock(int x, int y, int z);

    @Override
    public native void setBlock(int x, int y, int z, Block block);

    @Override
    public native BlockState getBlockState(int x, int y, int z);

    @Override
    public native void setBlockState(int x, int y, int z, BlockState state);

    @Override
    public native byte getBlockLight(int x, int y, int z);

    @Override
    public native void setBlockLight(int x, int y, int z, byte blockLight);

    @Override
    public native byte getSkyLight(int x, int y, int z);

    @Override
    public native void setSkyLight(int x, int y, int z, byte skyLight);

    @Override
    public native boolean hasSkylight();

    @Override
    public native Collection<Block> getBlocks();

    @Override
    public native BlockDataStorage clone();
}
