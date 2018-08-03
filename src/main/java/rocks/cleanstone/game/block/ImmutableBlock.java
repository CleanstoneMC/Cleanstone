package rocks.cleanstone.game.block;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import rocks.cleanstone.game.material.block.BlockType;

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

    public static ImmutableBlock of(BlockType blockType, byte metadata) {
        return of(BlockState.of(blockType, metadata));
    }

    @Override
    public BlockState getState() {
        return state;
    }

    @Override
    public String toString() {
        return "ImmutableBlock{" + state.getBlockType().getID() + "|" + state.getMetadata() + "}";
    }
}
