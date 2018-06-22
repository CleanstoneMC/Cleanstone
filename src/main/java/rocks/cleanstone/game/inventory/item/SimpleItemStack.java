package rocks.cleanstone.game.inventory.item;

import rocks.cleanstone.data.vanilla.nbt.NamedBinaryTag;
import rocks.cleanstone.game.material.item.ItemType;

public class SimpleItemStack implements ItemStack {
    private final short blockID;
    private final byte itemCount;
    private final short itemDamage;
    private final NamedBinaryTag nbt;

    public SimpleItemStack(short blockID, byte itemCount, short itemDamage, NamedBinaryTag nbt) {
        this.blockID = blockID;
        this.itemCount = itemCount;
        this.itemDamage = itemDamage;
        this.nbt = nbt;
    }

    public short getBlockID() {
        return blockID;
    }

    public byte getItemCount() {
        return itemCount;
    }

    public short getItemDamage() {
        return itemDamage;
    }

    public NamedBinaryTag getNbt() {
        return nbt;
    }

    @Override
    public ItemType getItemType() {
        return null;
    }

    @Override
    public int getAmount() {
        return 0;
    }
}
