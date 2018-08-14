package rocks.cleanstone.game.block.state;

import com.google.common.base.Preconditions;
import rocks.cleanstone.game.block.state.property.Property;
import rocks.cleanstone.game.material.block.BlockType;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * An immutable state of a block containing its material and properties
 */
public class BlockState {

    private static final Map<BlockType, Map<Map<Property<?>, ?>, BlockState>> BLOCK_STATE_CACHE = new ConcurrentHashMap<>();

    private final BlockType blockType;
    private final Map<Property<?>, ?> propertyValueMap;

    protected BlockState(BlockType blockType, Map<Property<?>, ?> propertyValueMap) {
        Preconditions.checkNotNull(blockType, "blockType cannot be null");
        this.blockType = blockType;
        this.propertyValueMap = propertyValueMap;
    }

    protected BlockState(BlockType blockType) {
        this(blockType, Collections.emptyMap());
    }

    public static BlockState of(BlockType blockType, Map<Property<?>, ?> propertyValueMap) {
        return BLOCK_STATE_CACHE.computeIfAbsent(blockType, k -> new ConcurrentHashMap<>())
                .computeIfAbsent(propertyValueMap, k -> new BlockState(blockType, propertyValueMap));
    }

    public static <T> BlockState withProperty(Property<T> property, T value) {

    }

    public static BlockState of(BlockType blockType) {
        return of(blockType, Collections.emptyMap());
    }

    public BlockType getBlockType() {
        return blockType;
    }

    public <T> T getPropertyValue(Property<T> property) {
        //noinspection unchecked
        return (T) propertyValueMap.get(property);
    }
}
