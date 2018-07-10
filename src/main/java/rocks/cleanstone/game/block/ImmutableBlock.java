package rocks.cleanstone.game.block;

import java.util.Collection;

import io.netty.util.internal.ConcurrentSet;
import rocks.cleanstone.game.material.block.BlockType;

/**
 * A standard block in the world that cannot be changed without being replaced
 */
public class ImmutableBlock implements Block {

    private static final Collection<ImmutableBlock> CACHED_BLOCKS = new ConcurrentSet<>();

    private final BlockState state;

    private ImmutableBlock(BlockState state) {
        this.state = state;
    }

    public static ImmutableBlock of(BlockState state) {
        return CACHED_BLOCKS.stream().filter(b -> b.getState().equals(state)).findFirst()
                .orElseGet(() -> {
                    ImmutableBlock newBlock = new ImmutableBlock(state);
                    CACHED_BLOCKS.add(newBlock);
                    return newBlock;
                });
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
