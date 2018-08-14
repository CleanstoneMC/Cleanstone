package rocks.cleanstone.game.block.state.mapping;

import rocks.cleanstone.game.block.state.BlockState;

/**
 * Maps each BlockState to a unique ID
 */
public interface BlockStateMapping<T> {
    T getID(BlockState state);

    BlockState getState(T id);
}
