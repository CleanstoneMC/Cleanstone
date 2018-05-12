package rocks.cleanstone.game.inventory.item;

import rocks.cleanstone.game.material.item.ItemType;

public interface ItemStack {
    ItemType getItem();

    int getAmount();
}
