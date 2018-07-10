package rocks.cleanstone.game.inventory.item;

import java.io.Serializable;

import rocks.cleanstone.game.material.item.ItemType;

public interface ItemStack extends Serializable {
    ItemType getType();

    int getAmount();

    void setAmount(int amount);

    short getMetadata();
}
