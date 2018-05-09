package rocks.cleanstone.game.world.region.chunk;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import rocks.cleanstone.game.world.region.block.Block;

public class MapChunkTable implements ChunkTable {

    private final Table<Integer, Integer, HashMap<Integer, Block>> coordHeightMapTable;

    public MapChunkTable(Table<Integer, Integer, HashMap<Integer, Block>> coordHeightMapTable) {
        this.coordHeightMapTable = coordHeightMapTable;
    }

    public MapChunkTable() {
        coordHeightMapTable = HashBasedTable.create(Chunk.WIDTH, Chunk.WIDTH);
    }

    @Nullable
    public Block getBlock(int x, int y, int z) {
        return getHeightMap(x, z).get(y);
    }

    public Collection<Block> getBlocks() {
        Collection<Block> blockCollection = new HashSet<>();

        coordHeightMapTable.cellSet().forEach(integerIntegerHashMapCell -> {
            blockCollection.addAll(integerIntegerHashMapCell.getValue().values());
        });

        return blockCollection;
    }

    @Override
    public void setBlock(int x, int y, int z, Block block) {
        getHeightMap(x, z).put(y, block);
    }

    private HashMap<Integer, Block> getHeightMap(int x, int z) {
        if (!coordHeightMapTable.contains(x, z)) {
            coordHeightMapTable.put(x, z, new HashMap<>());
        }

        return coordHeightMapTable.get(x, z);
    }
}