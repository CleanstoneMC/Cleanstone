package rocks.cleanstone.game.block;

import rocks.cleanstone.game.block.state.BlockState;

/**
 * A standard block in the world that cannot be changed without being replaced
 */
public class ImmutableBlock implements Block {

    private final BlockState state;

    /**
     * @param state The Blockstate for the Block
     * @deprecated Use the {@link ImmutableBlockProvider}
     */
    @SuppressWarnings("DeprecatedIsStillUsed")
    public ImmutableBlock(BlockState state) {
        this.state = state;
    }

    @Override
    public BlockState getState() {
        return state;
    }

    @Override
    public String toString() {
        return "ImmutableBlock{" + state.getBlockType().getID() + "|" + state.getProperties() + "}";
    }
}
