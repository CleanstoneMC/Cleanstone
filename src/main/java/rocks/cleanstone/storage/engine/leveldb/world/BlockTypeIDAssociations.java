package rocks.cleanstone.storage.engine.leveldb.world;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.Map;

import rocks.cleanstone.game.material.block.BlockType;

public class BlockTypeIDAssociations {

    private final BiMap<BlockType, Integer> blockTypeIDMap = HashBiMap.create();

    private int largestAssignedNumericID;

    public BlockTypeIDAssociations(Map<BlockType, Integer> blockTypeIDMap, int largestAssignedNumericID) {
        this.blockTypeIDMap.putAll(blockTypeIDMap);
        this.largestAssignedNumericID = largestAssignedNumericID;
    }

    public BlockTypeIDAssociations() {
        largestAssignedNumericID = 0;
    }

    public BiMap<BlockType, Integer> getBlockTypeIDMap() {
        return blockTypeIDMap;
    }

    public int getLargestAssignedNumericID() {
        return largestAssignedNumericID;
    }

    public void setLargestAssignedNumericID(int largestAssignedNumericID) {
        this.largestAssignedNumericID = largestAssignedNumericID;
    }
}
