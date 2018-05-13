package rocks.cleanstone.game.block;

import java.util.Collection;
import java.util.HashSet;

import rocks.cleanstone.game.material.Material;
import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.game.material.block.BlockType;

/**
 * A standard block in the world that cannot be changed without being replaced
 */
public class ImmutableBlock implements Block {

    private static final Collection<ImmutableBlock> CACHED_BLOCKS = new HashSet<>();

    private final BlockState state;
    private final BlockType type;

    private ImmutableBlock(BlockState state, BlockType type) {
        this.state = state;
        this.type = type;
    }

    private ImmutableBlock(BlockState state) {
        this(state, MaterialRegistry.getBlockType(state.getMaterial()));
    }

    public static ImmutableBlock of(BlockState state) {
        return CACHED_BLOCKS.stream().filter(b -> b.getState().equals(state)).findFirst()
                .orElseGet(() -> {
                    ImmutableBlock newBlock = new ImmutableBlock(state);
                    CACHED_BLOCKS.add(newBlock);
                    return newBlock;
                });
    }

    public static ImmutableBlock of(Material material) {
        return of(BlockState.of(material));
    }

    public static ImmutableBlock of(Material material, byte metadata) {
        return of(BlockState.of(material, metadata));
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
