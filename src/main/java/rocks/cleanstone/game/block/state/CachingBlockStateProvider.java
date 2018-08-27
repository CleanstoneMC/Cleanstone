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
        System.out.println("two "  + blockType.hashCode());
        return new BlockState(blockType, properties);
    }

    @Cacheable(value = "blockStates", sync = true)
    public BlockState of(BlockType blockType) {
        System.out.println("one "  + blockType.hashCode());
        return BlockState.of(blockType, new Properties(blockType.getProperties()));
    }

    @Cacheable(value = "blockStates", sync = true)
    public <T> BlockState withProperty(BlockType blockType, Property<T> property, T value) {
        System.out.println("three "  + blockType.hashCode());
        Properties properties = new PropertiesBuilder(blockType).withProperty(property, value).create();

        return BlockState.of(blockType, properties);
    }
}