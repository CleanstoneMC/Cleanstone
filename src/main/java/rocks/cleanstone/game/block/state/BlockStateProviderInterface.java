package rocks.cleanstone.game.block.state;

import rocks.cleanstone.game.block.state.property.Properties;
import rocks.cleanstone.game.block.state.property.Property;
import rocks.cleanstone.game.material.block.BlockType;

public interface BlockStateProviderInterface {

    BlockState of(BlockType blockType, Properties properties);

    BlockState of(BlockType blockType);

    <T> BlockState withProperty(BlockType blockType, Property<T> property, T value);
}
