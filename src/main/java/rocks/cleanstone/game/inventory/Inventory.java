package rocks.cleanstone.game.inventory;

import java.util.Collection;
import java.util.Map;

import rocks.cleanstone.game.inventory.item.ItemStack;
import rocks.cleanstone.game.material.item.ItemType;

public interface Inventory {

    InventoryType getType();

    int getSize();

    ItemStack getItemStackInSlot(int slot);

    Collection<ItemStack> getAllItemStacks();

    Map<ItemType, Integer> getAllItems();

    boolean addItem(ItemType item, int amount);

    boolean removeItem(ItemType item, int amount);
}
