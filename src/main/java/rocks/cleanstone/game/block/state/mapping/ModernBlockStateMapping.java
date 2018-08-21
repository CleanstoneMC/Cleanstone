package rocks.cleanstone.game.block.state.mapping;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.block.state.property.PropertiesBuilder;
import rocks.cleanstone.game.block.state.property.PropertyDefinition;
import rocks.cleanstone.game.material.block.BlockType;

import java.util.Arrays;
import java.util.Map;
import java.util.NavigableMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class ModernBlockStateMapping implements BlockStateMapping<Integer> {

    private final Map<BlockType, Integer> blockTypeBaseStateIDMap;
    private final Map<BlockType, PropertyDefinition[]> blockTypeDefaultPropertiesMap;
    private final NavigableMap<Integer, BlockType> baseStateIDBlockTypeMap;
    private final BlockState defaultState;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public ModernBlockStateMapping(ModernBlockStateMapping defaultMapping) {
        blockTypeBaseStateIDMap = new ConcurrentHashMap<>(defaultMapping.blockTypeBaseStateIDMap);
        blockTypeDefaultPropertiesMap = new ConcurrentHashMap<>(defaultMapping.blockTypeDefaultPropertiesMap);
        baseStateIDBlockTypeMap = new ConcurrentSkipListMap<>(defaultMapping.baseStateIDBlockTypeMap);
        defaultState = null;
    }

    public ModernBlockStateMapping(BlockState defaultState) {
        this.defaultState = defaultState;
        blockTypeBaseStateIDMap = new ConcurrentHashMap<>();
        blockTypeDefaultPropertiesMap = new ConcurrentHashMap<>();
        baseStateIDBlockTypeMap = new ConcurrentSkipListMap<>();
    }

    public int getBaseID(BlockType blockType) {
        return blockTypeBaseStateIDMap.get(blockType);
    }

    public void setBaseID(BlockType blockType, int baseID) {
        blockTypeBaseStateIDMap.put(blockType, baseID);
        baseStateIDBlockTypeMap.put(baseID, blockType);
    }

    public void shiftBiggerBaseIDs(int startBaseID, int shiftAmount) {
        ImmutableMap.copyOf(blockTypeBaseStateIDMap).forEach((blockType, baseStateID) -> {
            if (baseStateID >= startBaseID) {
                blockTypeBaseStateIDMap.remove(blockType, baseStateID);
                blockTypeBaseStateIDMap.put(blockType, baseStateID + shiftAmount);
                baseStateIDBlockTypeMap.remove(baseStateID, blockType);
                baseStateIDBlockTypeMap.put(baseStateID + shiftAmount, blockType);
            }
        });
    }

    public PropertyDefinition[] getDefaultProperties(BlockType blockType) {
        return blockTypeDefaultPropertiesMap.get(blockType);
    }

    public void setDefaultProperties(BlockType blockType, PropertyDefinition[] properties) {
        blockTypeDefaultPropertiesMap.put(blockType, properties);
    }

    @Override
    public Integer getID(BlockState state) {
        logger.debug("searching ID for state " + state);
        Integer baseID = blockTypeBaseStateIDMap.getOrDefault(state.getBlockType(), null);
        if (baseID != null) {
            PropertyDefinition[] properties = blockTypeDefaultPropertiesMap.get(state.getBlockType());
            if (properties == null) {
                properties = state.getBlockType().getProperties();
            }
            return serializeState(state, properties, baseID);
        } else if (defaultState != null && state != defaultState) {
            return getID(defaultState);
        } else {
            throw new IllegalStateException("There is neither an explicit or inherited baseID for "
                    + state + " nor for the default state " + defaultState);
        }
    }

    @Override
    public BlockState getState(Integer id) {
        logger.debug("searching state for id " + id);

        try {
            Map.Entry<Integer, BlockType> entry = baseStateIDBlockTypeMap.floorEntry(id);
            Preconditions.checkNotNull(entry);
            int baseID = entry.getKey();
            logger.debug("floor baseID is " + baseID);

            BlockType blockType = entry.getValue();
            PropertyDefinition[] properties = blockTypeDefaultPropertiesMap.get(blockType);
            if (properties == null) {
                properties = blockType.getProperties();
            }
            return deserializeState(id, blockType, properties, baseID);
        } catch (IllegalArgumentException e) {
            if (defaultState != null) {
                return defaultState;
            } else {
                throw new IllegalStateException("There is neither an explicit or inherited state for "
                        + id + " and there is no default state");
            }
        }
    }

    private int serializeState(BlockState state, PropertyDefinition[] propertyDefinitions, int baseID) {
        BlockType blockType = state.getBlockType();
        int temp = 0;
        for (PropertyDefinition propertyDefinition : propertyDefinitions) {
            //noinspection unchecked
            int serializedProperty = propertyDefinition.getProperty().serialize(state.getProperty(propertyDefinition.getProperty()));
            temp = serializedProperty + propertyDefinition.getProperty().getTotalValuesAmount() * temp;
        }
        return baseID + temp;
    }

    private BlockState deserializeState(int id, BlockType blockType, PropertyDefinition[] propertyDefinitions, int baseID) {
        PropertiesBuilder builder = new PropertiesBuilder(blockType);
        int propertyIDAmount = Arrays.stream(propertyDefinitions).mapToInt(definition -> definition.getProperty().getTotalValuesAmount())
                .reduce(1, (a, b) -> a * b);
        id -= baseID;
        Preconditions.checkArgument(id <= propertyIDAmount);
        int base = 0;
        for (PropertyDefinition propertyDefinition : propertyDefinitions) {
            propertyIDAmount /= propertyDefinition.getProperty().getTotalValuesAmount();
            int minPropertyID = id - id % propertyIDAmount;
            int valueIndex = (minPropertyID - base) / propertyIDAmount;
            Object propertyValue = propertyDefinition.getProperty().deserialize(valueIndex);
            // noinspection unchecked
            builder.withProperty(propertyDefinition, propertyValue);
            base = minPropertyID;
        }
        return BlockState.of(blockType, builder.create());
    }

    public BlockState getDefaultState() {
        return defaultState;
    }
}
