package rocks.cleanstone.game.block;

import lombok.extern.slf4j.Slf4j;
import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.block.state.property.Properties;
import rocks.cleanstone.game.material.block.BlockType;

import java.util.Objects;

/**
 * A standard block in the world that cannot be changed without being replaced
 */
@Slf4j
public class ImmutableBlock implements Block {
    private static CachingImmutableBlockProvider loadingSource;
    private final BlockState state;

    /**
     * @param state The Blockstate for the Block
     */
    ImmutableBlock(BlockState state) {
        this.state = state;
    }

    private static CachingImmutableBlockProvider getLoadingSource() {
        return Objects.requireNonNullElseGet(loadingSource, () -> (loadingSource = new CachingImmutableBlockProvider()));
    }

    static void setLoadingSource(CachingImmutableBlockProvider loadingSource) {
        ImmutableBlock.loadingSource = loadingSource;
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
