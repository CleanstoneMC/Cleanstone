package rocks.cleanstone.game.block;

import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.block.state.property.Properties;
import rocks.cleanstone.game.material.block.BlockType;

public interface ImmutableBlockProvider {

    ImmutableBlock of(BlockState state);

    ImmutableBlock of(BlockType blockType);

    ImmutableBlock of(BlockType blockType, Properties properties);
}
