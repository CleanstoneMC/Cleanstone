package rocks.cleanstone.game.block;

import com.google.common.base.Preconditions;

import java.util.Collection;
import java.util.HashSet;

import rocks.cleanstone.game.material.VanillaMaterial;
import rocks.cleanstone.game.material.block.BlockType;
import rocks.cleanstone.game.material.block.vanilla.VanillaBlockTypes;

/**
 * A standard block in the world that cannot be changed without being replaced
 */
public class ImmutableBlock implements Block {

    private static final Collection<ImmutableBlock> cachedBlocks = new HashSet<>();

    private final BlockType blockType;

    private ImmutableBlock(BlockType blockType) {
        this.blockType = blockType;
    }

    public static ImmutableBlock of(BlockType blockType) {
        Preconditions.checkArgument(blockType.hasBlockEntity(), "blockType cannot have blockEntity");
        return cachedBlocks.stream().filter(b -> b.getBlockType() == blockType).findFirst()
                .orElseGet(() -> {
                    ImmutableBlock newBlock = new ImmutableBlock(blockType);
                    cachedBlocks.add(newBlock);
                    return newBlock;
                });
    }

    public static ImmutableBlock of(VanillaMaterial material) {
        return of(VanillaBlockTypes.of(material));
    }

    @Override
    public BlockType getBlockType() {
        return blockType;
    }
}
