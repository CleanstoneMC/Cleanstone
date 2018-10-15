package rocks.cleanstone.game.inventory.item;

import rocks.cleanstone.game.material.item.ItemType;

import java.io.Serializable;

public interface ItemStack extends Serializable {
    ItemType getType();

    int getAmount();

    void setAmount(int amount);
}
