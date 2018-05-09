package rocks.cleanstone.game.world.region.chunk;

import org.springframework.lang.Nullable;
import rocks.cleanstone.game.world.region.block.Block;
import rocks.cleanstone.utils.UnsafeCall;

import java.util.Collection;
import java.util.HashSet;

public class ArrayChunkTable implements ChunkTable {

    private int[][][] blockData;
    private Block[][][] blockCache;

    public ArrayChunkTable() {
        blockData = new int[16][16][256];
        blockCache = new Block[16][16][256];
    }

    @Override
    @Nullable
    public Block getBlock(int x, int y, int z) {
        final int currentBlockData = getBlockData(x, y, z);

        if (currentBlockData == 0) {
            if (blockCache[x][z][y] != null) {
                blockCache[x][z][y] = null;
            }

            return null;
        } else {
            Block currentBlock = blockCache[x][z][y];

            if (currentBlock != null) {
                if (currentBlock.getMaterial().getBlockID() == currentBlockData) {
                    return currentBlock;
                }
            }

            currentBlock = getBlockFromBlockData(currentBlockData);

            blockCache[x][z][y] = currentBlock;

            return currentBlock;
        }
    }

    public void setBlock(int x, int y, int z, Block block) {
        if (blockCache[x][z][y].equals(block)) {
            return;
        }

        blockCache[x][z][y] = block;

        int currentBlockData = getBlockDataFromBlock(block);

        blockData[x][z][y] = currentBlockData;
    }

    @Override
    public Collection<Block> getBlocks() {
        return new HashSet<>();
    }

    @UnsafeCall
    public Block getBlockFromBlockData(int blockData) {
        return null; //TODO
    }

    @UnsafeCall
    public int getBlockDataFromBlock(Block block) {
        return 0;
    }

    @UnsafeCall
    public int getBlockData(int x, int y, int z) {
        return blockData[x][z][y];
    }

    @UnsafeCall
    public void setBlockData(int x, int y, int z, int newBlockData) {
        blockData[x][z][y] = newBlockData;
    }
}
