package rocks.cleanstone.game.inventory;

import rocks.cleanstone.game.inventory.item.ItemStack;
import rocks.cleanstone.game.material.item.ItemType;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

public interface Inventory extends Serializable {

    InventoryType getType();

    int getSize();

    ItemStack getItemStackInSlot(int slot);

    void setItemInSlot(int slot, ItemStack itemStack);

    Collection<ItemStack> getAllItemStacks();

    Map<ItemType, Integer> getAllItems();

    boolean addItem(ItemType item, int amount);

    boolean removeItem(ItemType item, int amount);
}
