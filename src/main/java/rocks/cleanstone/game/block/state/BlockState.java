package rocks.cleanstone.game.block.state;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import rocks.cleanstone.game.block.state.property.Properties;
import rocks.cleanstone.game.block.state.property.Property;
import rocks.cleanstone.game.material.block.BlockType;

/**
 * An immutable state of a block containing its material and properties
 */
@Slf4j
public class BlockState {
    private static CachingBlockStateProvider loadingSource;

    static void setLoadingSource(CachingBlockStateProvider loadingSource) {
        BlockState.loadingSource = loadingSource;
    }

    private static CachingBlockStateProvider getLoadingSource() {
        if (loadingSource == null) {
            return (loadingSource = new CachingBlockStateProvider());
        } else {
            return loadingSource;
        }
    }

    public static BlockState of(BlockType blockType, Properties properties) {
        return getLoadingSource().of(blockType, properties);
    }

    public static BlockState of(BlockType blockType) {
        return getLoadingSource().of(blockType);
    }

    public static <T> BlockState withProperty(BlockType blockType, Property<T> property, T value) {
        return getLoadingSource().withProperty(blockType, property, value);
    }

    private final BlockType blockType;
    private final Properties properties;

    BlockState(BlockType blockType, Properties properties) {
        Preconditions.checkNotNull(blockType, "blockType cannot be null");
        this.blockType = blockType;
        this.properties = properties;
    }

    public BlockType getBlockType() {
        return blockType;
    }

    public <T> T getProperty(Property<T> property) {
        return properties.get(property);
    }

    public Properties getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        if (properties.getPropertyValuePairs().isEmpty()) {
            return "BlockState{" + blockType + "}";
        } else {
            return "BlockState{" + blockType + "; " + properties + "}";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BlockState)) {
            return false;
        }
        final BlockState that = (BlockState) o;
        return Objects.equal(blockType, that.blockType) &&
                Objects.equal(properties, that.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(blockType, properties);
    }
}
