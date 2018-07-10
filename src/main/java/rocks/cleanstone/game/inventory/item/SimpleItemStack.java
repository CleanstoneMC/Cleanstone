package rocks.cleanstone.game.inventory.item;

import com.google.common.base.Preconditions;

import rocks.cleanstone.data.vanilla.nbt.NamedBinaryTag;
import rocks.cleanstone.game.material.item.ItemType;

/**
 * Corresponds to Minecraft's Slot data type
 */
public class SimpleItemStack implements ItemStack {
    private final ItemType itemType;
    private final short metadata;
    private final NamedBinaryTag nbt;
    private int amount;

    public SimpleItemStack(ItemType itemType, int amount, short metadata, NamedBinaryTag nbt) {
        this.itemType = itemType;
        this.amount = amount;
        this.metadata = metadata;
        this.nbt = nbt;
    }

    /**
     * Corresponds to a Minecraft item's damage value
     */
    public short getMetadata() {
        return metadata;
    }

    public NamedBinaryTag getNbt() {
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
