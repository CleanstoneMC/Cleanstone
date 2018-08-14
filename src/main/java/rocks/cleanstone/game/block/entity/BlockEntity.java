package rocks.cleanstone.game.block.entity;

import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.state.BlockState;

/**
 * A block in the world with mutable state information and behavior optionally
 */
public abstract class BlockEntity implements Block {

    private final BlockState state;

    public BlockEntity(BlockState state) {
        this.state = state;
    }

    @Override
    public BlockState getState() {
        return state;
    }
}
