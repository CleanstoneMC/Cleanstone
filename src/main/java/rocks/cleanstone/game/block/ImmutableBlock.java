package rocks.cleanstone.game.block;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.block.state.property.Properties;
import rocks.cleanstone.game.material.block.BlockType;

/**
 * A standard block in the world that cannot be changed without being replaced
 */
public class ImmutableBlock implements Block {
    private static CachingImmutableBlockProvider loadingSource;
    private static Logger log = LoggerFactory.getLogger(ImmutableBlock.class);

    static void setLoadingSource(CachingImmutableBlockProvider loadingSource) {
        ImmutableBlock.loadingSource = loadingSource;
    }

    private static CachingImmutableBlockProvider getLoadingSource() {
        if (loadingSource == null) {
            return (loadingSource = new CachingImmutableBlockProvider());
        } else {
            return loadingSource;
        }
    }

    public static ImmutableBlock of(BlockState state) {
        return getLoadingSource().of(state);
    }

    public static ImmutableBlock of(BlockType blockType) {
        return getLoadingSource().of(blockType);
    }

    public static ImmutableBlock of(BlockType blockType, Properties properties) {
        return getLoadingSource().of(blockType, properties);
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
        if (state.getProperties().getPropertyValuePairs().isEmpty()) {
            return "ImmutableBlock{" + state.getBlockType() + "}";
        } else {
            return "ImmutableBlock{" + state.getBlockType() + "; " + state.getProperties() + "}";
        }
    }
}
