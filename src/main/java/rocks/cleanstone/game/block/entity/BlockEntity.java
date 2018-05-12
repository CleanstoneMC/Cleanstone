package rocks.cleanstone.game.block.entity;

import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.material.block.BlockType;

/**
 * A block in the world with mutable state information and behavior optionally
 */
public abstract class BlockEntity implements Block {

    private final BlockType blockType;

    public BlockEntity(BlockType blockType) {
        this.blockType = blockType;
    }

    public BlockType getBlockType() {
        return blockType;
    }
}
