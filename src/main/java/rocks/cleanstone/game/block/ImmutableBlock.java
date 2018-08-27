package rocks.cleanstone.game.block;

import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.block.state.property.Properties;
import rocks.cleanstone.game.material.block.BlockType;

/**
 * A standard block in the world that cannot be changed without being replaced
 */
public class ImmutableBlock implements Block {
    private static CachingImmutableBlockProvider loadingSource;

    static void setLoadingSource(CachingImmutableBlockProvider loadingSource) {
        ImmutableBlock.loadingSource = loadingSource;
    }

    public static ImmutableBlock of(BlockState state) {
        return loadingSource.of(state);
    }

    public static ImmutableBlock of(BlockType blockType) {
        return loadingSource.of(blockType);
    }

    public static ImmutableBlock of(BlockType blockType, Properties properties) {
        return loadingSource.of(blockType, properties);
    }

    private final BlockState state;

    /**
     * @param state The Blockstate for the Block
     */
    ImmutableBlock(BlockState state) {
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
