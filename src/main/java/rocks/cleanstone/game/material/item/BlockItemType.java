package rocks.cleanstone.game.material.item;

import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.Face;
import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.game.inventory.item.ItemStack;
import rocks.cleanstone.game.material.block.BlockType;

public class BlockItemType implements ItemType {

    private final BlockType blockType;

    public BlockItemType(BlockType blockType) {
        this.blockType = blockType;
    }

    @Override
    public void rightClickAir(Entity entity, ItemStack holding) {
    }

    @Override
    public void rightClickBlock(Entity entity, Block block, Face face) {
        // TODO trigger place
    }

    @Override
    public int getStackSize() {
        return 64;
    }

    @Override
    public int getID() {
        return blockType.getID();
    }

    @Override
    public String getMinecraftID() {
        return blockType.getMinecraftID();
    }
}
