package rocks.cleanstone.game.block.state;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import rocks.cleanstone.game.block.state.property.Properties;
import rocks.cleanstone.game.block.state.property.Property;
import rocks.cleanstone.game.material.block.BlockType;

/**
 * An immutable state of a block containing its material and properties
 */
public class BlockState {

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
        return SimpleBlockStateProvider.get().of(blockType, properties);
    }

    public static BlockState of(BlockType blockType) {
        return SimpleBlockStateProvider.get().of(blockType);
    }

    public BlockType getBlockType() {
        return blockType;
    }

    public <T> T getProperty(Property<T> property) {
        return properties.get(property);
    }

    public <T> BlockState withProperty(Property<T> property, T value) {
        return SimpleBlockStateProvider.get().withProperty(blockType, property, value);
    }

    public Properties getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        return "BlockState{type=" + blockType + ";properties=" + properties + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockState)) return false;
        BlockState that = (BlockState) o;
        return Objects.equal(blockType, that.blockType) &&
                Objects.equal(properties, that.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(blockType, properties);
    }
}
