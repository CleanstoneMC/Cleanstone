package rocks.cleanstone.game.block.state;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.block.state.property.Properties;
import rocks.cleanstone.game.block.state.property.PropertiesBuilder;
import rocks.cleanstone.game.block.state.property.Property;
import rocks.cleanstone.game.material.block.BlockType;
import java.util.Map;
import java.util.HashMap;

@Component("blockStateProvider")
public class CachingBlockStateProvider {
    private final Map<BlockType, Map<Properties, BlockState>> blockStateCache = new HashMap<>();

    public CachingBlockStateProvider() {
        BlockState.setLoadingSource(this);
    }

    // @Cacheable(value = "blockStates", sync = true)
    public BlockState of(BlockType blockType, Properties properties) {
        return blockStateCache.computeIfAbsent(
                blockType,
                b -> new HashMap<>()
        ).computeIfAbsent(properties, p -> new BlockState(blockType, properties));
    }

    // @Cacheable(value = "blockStates", sync = true)
    public BlockState of(BlockType blockType) {
        return BlockState.of(blockType, new Properties(blockType.getProperties()));
    }

    // @Cacheable(value = "blockStates", sync = true)
    public <T> BlockState withProperty(BlockType blockType, Property<T> property, T value) {
        Properties properties = new PropertiesBuilder(blockType).withProperty(property, value).create();

        return BlockState.of(blockType, properties);
    }
}
