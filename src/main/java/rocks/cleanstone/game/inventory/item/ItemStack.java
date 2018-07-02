package rocks.cleanstone.game.inventory.item;

import rocks.cleanstone.game.material.item.ItemType;

import java.io.Serializable;

public interface ItemStack extends Serializable {
    ItemType getItemType();

    int getAmount();

    void setAmount(int amount);

    short getMetadata();
}
