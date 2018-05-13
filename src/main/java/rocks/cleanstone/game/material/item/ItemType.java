package rocks.cleanstone.game.material.item;

import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.BlockFace;
import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.game.inventory.item.ItemStack;
import rocks.cleanstone.game.material.Material;

/**
 * An item type in the game that defines the behavior of items with this type
 */
public interface ItemType {

    Material getMaterial();

    int getStackSize();

    void rightClickAir(Entity entity, ItemStack holding);

    void rightClickBlock(Entity entity, Block block, BlockFace blockFace);
}
