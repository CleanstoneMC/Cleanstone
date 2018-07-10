package rocks.cleanstone.game.inventory;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import rocks.cleanstone.game.inventory.item.ItemStack;
import rocks.cleanstone.game.material.item.ItemType;

public class SimpleInventory implements Inventory {
    private int size;
    private ItemStack[] inventory;

    public SimpleInventory(int size) {
        this.size = size;
        this.inventory = new ItemStack[size];
    }

    @Override
    public InventoryType getType() {
        return null;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public ItemStack getItemStackInSlot(int slot) {
        if (slot > size) {
            return null;
        }

        return inventory[slot];
    }

    @Override
    public void setItemInSlot(int slot, ItemStack itemStack) {
        inventory[slot] = itemStack;
    }

    @Override
    public Collection<ItemStack> getAllItemStacks() {
        return Arrays.asList(inventory);
    }

    @Override
    public Map<ItemType, Integer> getAllItems() {
        Map<ItemType, Integer> itemStacks = new HashMap<>();

        for (ItemStack itemStack : inventory) {
            if (itemStack == null) {
                continue;
            }

            int itemAmount = itemStacks.getOrDefault(itemStack.getType(), 0) + itemStack.getAmount();
            itemStacks.put(itemStack.getType(), itemAmount);
        }

        return itemStacks;
    }

    @Override
    public boolean addItem(ItemType item, int amount) {
        ItemStack[] inventoryClone = inventory.clone();

        int remaining = amount;
        for (ItemStack itemStack : inventoryClone) {
            if (itemStack.getType() == item && itemStack.getAmount() < 64) {
                int leftSpace = (itemStack.getAmount() - 64) * -1;

                remaining = remaining - leftSpace;

                itemStack.setAmount(itemStack.getAmount() + leftSpace);
                if (remaining < 0) {
                    inventory = inventoryClone;
                    return true;
                }

            }
        }

        for (int i = 0; i < inventoryClone.length; i++) {
            if (inventoryClone[i] == null) {
                //TODO: Add Itemstack of item
            }
        }

        return false;
    }

    @Override
    public boolean removeItem(ItemType item, int amount) {
        //TODO: Implement me!
        return false;
    }
}
