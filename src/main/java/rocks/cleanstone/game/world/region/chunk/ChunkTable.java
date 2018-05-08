package rocks.cleanstone.game.world.region.chunk;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.springframework.lang.Nullable;
import rocks.cleanstone.game.world.region.block.Block;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class ChunkTable {

    private final Table<Integer, Integer, HashMap<Integer, Block>> coordHeightMapTable;

    public ChunkTable(Table<Integer, Integer, HashMap<Integer, Block>> coordHeightMapTable) {
        this.coordHeightMapTable = coordHeightMapTable;
    }

    public ChunkTable() {
        coordHeightMapTable = HashBasedTable.create(16, 16);;
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

    private HashMap<Integer, Block> getHeightMap(int x, int z) {
        if (!coordHeightMapTable.contains(x, z)) {
            coordHeightMapTable.put(x, z, new HashMap<>());
        }

        return coordHeightMapTable.get(x, z);
    }
}
