package rocks.cleanstone.game.block.state;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.block.state.property.Properties;
import rocks.cleanstone.game.block.state.property.PropertiesBuilder;
import rocks.cleanstone.game.block.state.property.Property;
import rocks.cleanstone.game.material.block.BlockType;

@Component("blockStateProvider")
public class CachingBlockStateProvider {

    @Cacheable(value = "blockStates", sync = true)
    public BlockState of(BlockType blockType, Properties properties) {
        return new BlockState(blockType, properties);
    }

    @Cacheable(value = "blockStates", sync = true)
    public BlockState of(BlockType blockType) {
        return BlockState.of(blockType, new Properties(blockType.getProperties()));
    }

    @Cacheable(value = "blockStates", sync = true)
    public <T> BlockState withProperty(BlockType blockType, Property<T> property, T value) {
        Properties properties = new PropertiesBuilder(blockType).withProperty(property, value).create();

        return BlockState.of(blockType, properties);
    }
}