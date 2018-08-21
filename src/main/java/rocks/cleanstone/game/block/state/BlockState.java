package rocks.cleanstone.game.block.state;

import com.google.common.base.Preconditions;
import rocks.cleanstone.game.block.state.property.Properties;
import rocks.cleanstone.game.block.state.property.PropertiesBuilder;
import rocks.cleanstone.game.block.state.property.Property;
import rocks.cleanstone.game.block.state.property.PropertyDefinition;
import rocks.cleanstone.game.material.block.BlockType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * An immutable state of a block containing its material and properties
 */
public class BlockState {

    private static final Map<BlockType, Map<Properties, BlockState>> BLOCK_STATE_CACHE = new ConcurrentHashMap<>();

    private final BlockType blockType;
    private final Properties properties;

    protected BlockState(BlockType blockType, Properties properties) {
        Preconditions.checkNotNull(blockType, "blockType cannot be null");
        this.blockType = blockType;
        this.properties = properties;
    }

    protected BlockState(BlockType blockType) {
        this(blockType, new Properties(blockType.getProperties()));
    }

    public static BlockState of(BlockType blockType, Properties properties) {
        return BLOCK_STATE_CACHE.computeIfAbsent(blockType, k -> new ConcurrentHashMap<>())
                .computeIfAbsent(properties, k -> new BlockState(blockType, properties));
    }

    public static BlockState of(BlockType blockType) {
        return of(blockType, new Properties(blockType.getProperties()));
    }

    public BlockType getBlockType() {
        return blockType;
    }

    public <T> T getProperty(Property<T> property) {
        return properties.get(property);
    }

    public <T> T getProperty(String propertyName) {
        return properties.get(propertyName);
    }

    public <T> T getProperty(Class<T> valueClass) {
        return properties.get(valueClass);
    }

    public <T> BlockState withProperty(PropertyDefinition<T> propertyDefinition, T value) {
        Properties properties = new PropertiesBuilder(this.properties)
                .withProperty(propertyDefinition, value).create();
        return of(blockType, properties);
    }

    public <T> BlockState withProperty(String propertyName, T value) {
        Properties properties = new PropertiesBuilder(this.properties)
                .withProperty(propertyName, value, blockType).create();
        return of(blockType, properties);
    }

    public <T> BlockState withProperty(Class<T> valueClass, T value) {
        Properties properties = new PropertiesBuilder(this.properties)
                .withProperty(valueClass, value, blockType).create();
        return of(blockType, properties);
    }

    public Properties getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        return "BlockState{type=" + blockType + ";properties=" + properties + "}";
    }
}
