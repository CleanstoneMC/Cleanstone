package rocks.cleanstone.game.entity.types;

import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.game.inventory.item.ItemStack;

public interface Item extends Entity {
    ItemStack getItemStack();

    void setItemStack(ItemStack itemStack);
}
