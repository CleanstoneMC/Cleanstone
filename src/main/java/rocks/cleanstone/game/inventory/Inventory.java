package rocks.cleanstone.game.inventory;

import rocks.cleanstone.game.inventory.item.Item;
import rocks.cleanstone.game.inventory.item.ItemStack;

import java.util.Collection;
import java.util.Map;

public interface Inventory {

    InventoryType getType();

    int getSize();

    ItemStack getItemStackInSlot(int slot);

    Collection<ItemStack> getAllItemStacks();

    Map<Item, Integer> getAllItems();

    boolean addItem(Item item, int amount);

    boolean removeItem(Item item, int amount);
}
