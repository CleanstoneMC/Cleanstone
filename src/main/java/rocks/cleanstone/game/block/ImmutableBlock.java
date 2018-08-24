package rocks.cleanstone.game.block;

import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.block.state.property.Properties;
import rocks.cleanstone.game.material.block.BlockType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A standard block in the world that cannot be changed without being replaced
 */
public class ImmutableBlock implements Block {

    private static final Map<BlockState, ImmutableBlock> CACHED_BLOCKS = new ConcurrentHashMap<>();

    private final BlockState state;

    private ImmutableBlock(BlockState state) {
        this.state = state;
    }

    public static ImmutableBlock of(BlockState state) {
        return CACHED_BLOCKS.computeIfAbsent(state, k -> new ImmutableBlock(state));
    }

    public static ImmutableBlock of(BlockType blockType) {
        return of(BlockState.of(blockType));
    }

    public static ImmutableBlock of(BlockType blockType, Properties properties) {
        return of(BlockState.of(blockType, properties));
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
