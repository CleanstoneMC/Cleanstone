package rocks.cleanstone.game.block.state.mapping;

import com.google.common.collect.Maps;
import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.block.state.property.Property;
import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.game.material.block.BlockType;

import java.util.Map;
import java.util.NavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class ModernBlockStateMapping implements BlockStateMapping<Integer> {

    private final Map<BlockType, Integer> blockTypeBaseStateIDMap = Maps.newConcurrentMap();
    private final Map<BlockType, Property[]> blockTypeDefaultPropertiesMap = Maps.newConcurrentMap();
    private final NavigableMap<Integer, BlockType> baseStateIDBlockTypeMap = new ConcurrentSkipListMap<>();
    private final MaterialRegistry materialRegistry;
    private final BlockStateMapping<Integer> defaultMapping;
    private final BlockState defaultState;

    public ModernBlockStateMapping(MaterialRegistry materialRegistry, BlockStateMapping<Integer> defaultMapping) {
        this.materialRegistry = materialRegistry;
        this.defaultMapping = defaultMapping;
        defaultState = null;
    }

    public ModernBlockStateMapping(MaterialRegistry materialRegistry, BlockState defaultState) {
        this.materialRegistry = materialRegistry;
        this.defaultState = defaultState;
        defaultMapping = null;
    }

    public int getBaseID(BlockType blockType) {
        return blockTypeBaseStateIDMap.get(blockType);
    }

    public void setBaseID(BlockType blockType, int baseID) {
        blockTypeBaseStateIDMap.put(blockType, baseID);
        baseStateIDBlockTypeMap.put(baseID, blockType);
    }

    public void shiftBiggerBaseIDs(int startBaseID, int shiftAmount) {

    }

    public Property[] getDefaultProperties(BlockType blockType) {
        return blockTypeDefaultPropertiesMap.get(blockType);
    }

    public void setDefaultProperties(BlockType blockType, Property[] properties) {
        blockTypeDefaultPropertiesMap.put(blockType, properties);
    }

    @Override
    public Integer getID(BlockState state) {
        int id = -1;
        if (defaultState != null) {
            id = state != defaultState ? getID(defaultState) : -1;
        } else if (defaultMapping != null) {
            id = defaultMapping.getID(state);
        }
        int baseID = blockTypeBaseStateIDMap.getOrDefault(state.getBlockType(), -1);
        if (baseID != -1) {
            return serializeState(baseID, state);
        } else {
            return id;
        }
    }

    private int serializeState(int baseID, BlockState state) {
        int mask = 0, neededBits = 0;
        Property[] properties = state.getBlockType().getProperties();
        for (int i = properties.length; i >= 0; i--) {
            Property property = properties[i];
            //noinspection unchecked
            int serializedProperty = property.serialize(state.getProperty(property));
            mask |= (serializedProperty << (neededBits += property.getNeededSerializationBitAmount()));
        }
        return baseID + mask;
    }

    private BlockState deserializeState(int id) {
        Map.Entry<Integer, BlockType> entry = baseStateIDBlockTypeMap.floorEntry(id);
        int baseID = entry.getKey();
        BlockType type = entry.getValue();
        Property[] properties = blockTypeDefaultPropertiesMap.get(type);
        if (properties == null) {
            properties = type.getProperties();
        }
        int mask = id - baseID;
        for (int i = properties.length; i >= 0; i--) {
            Property property = properties[i];
            // TODO fix modern blockState deserialization
            int serializedProperty = mask & property.getNeededSerializationBitAmount() << i; // (?)
            Object propertyValue = property.deserialize(serializedProperty);
        }
    }

    @Override
    public BlockState getState(Integer id) {
        return deserializeState(id);
    }
}
