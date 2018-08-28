package rocks.cleanstone.game.block.state;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.block.state.property.Properties;
import rocks.cleanstone.game.block.state.property.PropertiesBuilder;
import rocks.cleanstone.game.block.state.property.Property;
import rocks.cleanstone.game.material.block.BlockType;

@Component("blockStateProvider")
public class SimpleBlockStateProvider implements BlockStateProvider {

    @Cacheable("blockStates")
    @Override
    public BlockState of(BlockType blockType, Properties properties) {
        return new BlockState(blockType, properties);
    }

    @Cacheable("blockStates")
    @Override
    public BlockState of(BlockType blockType) {
        return new BlockState(blockType, new Properties(blockType.getProperties()));
    }

    @Cacheable("blockStates")
    @Override
    public <T> BlockState withProperty(BlockType blockType, Property<T> property, T value) {
        Properties properties = new PropertiesBuilder(blockType).withProperty(property, value).create();

        return new BlockState(blockType, properties);
    }
}