package rocks.cleanstone.game.inventory.item;

import com.github.steveice10.opennbt.tag.builtin.Tag;
import com.google.common.base.Preconditions;
import rocks.cleanstone.game.material.item.ItemType;

/**
 * Corresponds to Minecraft's Slot data type
 */
public class SimpleItemStack implements ItemStack {

    private final ItemType itemType;
    private final Tag nbt;
    private int amount;

    public SimpleItemStack(ItemType itemType, int amount, Tag nbt) {
        this.itemType = itemType;
        this.amount = amount;
        this.nbt = nbt;
    }

    public Tag getNbt() {
        return nbt;
    }

    @Override
    public ItemType getType() {
        return itemType;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setAmount(int amount) {
        Preconditions.checkArgument(amount <= Byte.MAX_VALUE && amount >= Byte.MIN_VALUE,
                "ItemStack amount out of range");
        this.amount = amount;
    }
}
