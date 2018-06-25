package rocks.cleanstone.game.inventory.item;

import rocks.cleanstone.data.vanilla.nbt.NamedBinaryTag;
import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.game.material.VanillaMaterial;
import rocks.cleanstone.game.material.item.ItemType;
import rocks.cleanstone.net.packet.data.Slot;

public class SimpleItemStack implements ItemStack {
    private final short blockID;
    private final byte itemCount;
    private final short itemDamage;
    private final NamedBinaryTag nbt;
    private int amount = 0;

    public SimpleItemStack(short blockID, byte itemCount, short itemDamage, NamedBinaryTag nbt) {
        this.blockID = blockID;
        this.itemCount = itemCount;
        this.itemDamage = itemDamage;
        this.nbt = nbt;
    }

    public static SimpleItemStack fromSlot(Slot slot) {
        return new SimpleItemStack(slot.getBlockID(), slot.getItemCount(), slot.getItemDamage(), slot.getNbt());
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
        return MaterialRegistry.getItemType(VanillaMaterial.byID(blockID)); //TODO: Don't use VanillaMaterial
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
