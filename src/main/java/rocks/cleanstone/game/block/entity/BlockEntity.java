package rocks.cleanstone.game.block.entity;

import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.BlockState;
import rocks.cleanstone.game.material.block.BlockType;
import rocks.cleanstone.game.material.block.vanilla.VanillaBlockTypes;

/**
 * A block in the world with mutable state information and behavior optionally
 */
public abstract class BlockEntity implements Block {

    private final BlockState state;
    private final BlockType type;

    public BlockEntity(BlockState state, BlockType type) {
        this.state = state;
        this.type = type;
    }

    public BlockEntity(BlockState state) {
        this(state, VanillaBlockTypes.get(state.getMaterial()));
    }

    @Override
    public BlockType getType() {
        return type;
    }

    @Override
    public BlockState getState() {
        return state;
    }
}
