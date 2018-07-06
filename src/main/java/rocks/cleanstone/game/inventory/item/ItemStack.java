package rocks.cleanstone.game.inventory.item;

import rocks.cleanstone.game.material.Material;
import rocks.cleanstone.game.material.item.ItemType;

public interface ItemStack {
    ItemType getItemType();

    int getAmount();

    void setAmount(int amount);

    short getMetadata();

    Material getMaterial();
}
