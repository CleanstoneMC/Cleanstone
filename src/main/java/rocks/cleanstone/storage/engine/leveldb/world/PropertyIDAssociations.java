package rocks.cleanstone.storage.engine.leveldb.world;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.Map;

import rocks.cleanstone.game.block.state.property.Property;

public class PropertyIDAssociations {

    private final BiMap<Property<?>, Integer> propertyIDMap = HashBiMap.create();

    private int largestAssignedNumericID;

    public PropertyIDAssociations(Map<Property<?>, Integer> propertyIDMap, int largestAssignedNumericID) {
        this.propertyIDMap.putAll(propertyIDMap);
        this.largestAssignedNumericID = largestAssignedNumericID;
    }

    public PropertyIDAssociations() {
        largestAssignedNumericID = 0;
    }

    public BiMap<Property<?>, Integer> getPropertyIDMap() {
        return propertyIDMap;
    }

    public int getLargestAssignedNumericID() {
        return largestAssignedNumericID;
    }

    public void setLargestAssignedNumericID(int largestAssignedNumericID) {
        this.largestAssignedNumericID = largestAssignedNumericID;
    }
}
