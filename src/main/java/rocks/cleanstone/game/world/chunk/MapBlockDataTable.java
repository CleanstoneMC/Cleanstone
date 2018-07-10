package rocks.cleanstone.game.world.chunk;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.material.block.vanilla.VanillaBlockType;

public class MapBlockDataTable implements BlockDataTable {

    private final Table<Integer, Integer, HashMap<Integer, Block>> coordHeightMapTable;

    public MapBlockDataTable(Table<Integer, Integer, HashMap<Integer, Block>> coordHeightMapTable) {
        this.coordHeightMapTable = coordHeightMapTable;
    }

    public MapBlockDataTable() {
        coordHeightMapTable = HashBasedTable.create(Chunk.WIDTH, Chunk.WIDTH);
    }

    @Nullable
    public Block getBlock(int x, int y, int z) {
        Block block = getHeightMap(x, z).get(y);
        return block != null ? block : ImmutableBlock.of(VanillaBlockType.AIR);
    }

    public Collection<Block> getBlocks() {
        Collection<Block> blockCollection = new HashSet<>();

        coordHeightMapTable.cellSet().forEach(integerIntegerHashMapCell ->
                blockCollection.addAll(integerIntegerHashMapCell.getValue().values()));

        return blockCollection;
    }

    @Override
    public void setBlock(int x, int y, int z, Block block) {
        if (block == null || block.getState().getBlockType() == VanillaBlockType.AIR) {
            getHeightMap(x, z).remove(y);
        } else
            getHeightMap(x, z).put(y, block);
    }

    @Override
    public byte getBlockLight(int x, int y, int z) {
        // TODO
        return 0;
    }

    @Override
    public void setBlockLight(int x, int y, int z, byte blockLight) {
        // TODO
    }

    @Override
    public byte getSkyLight(int x, int y, int z) {
        // TODO
        return 0;
    }

    @Override
    public void setSkyLight(int x, int y, int z, byte skyLight) {
        // TODO
    }

    @Override
    public boolean hasSkylight() {
        // TODO
        return false;
    }

    private HashMap<Integer, Block> getHeightMap(int x, int z) {
        if (!coordHeightMapTable.contains(x, z)) {
            coordHeightMapTable.put(x, z, new HashMap<>());
        }

        return coordHeightMapTable.get(x, z);
    }
}