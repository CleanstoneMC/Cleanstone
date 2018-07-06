package rocks.cleanstone.net.packet.outbound;

import rocks.cleanstone.game.inventory.item.ItemStack;
import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class SetSlotPacket implements Packet {

    private final byte windowID;
    private final short slot;
    private final ItemStack itemData;

    public SetSlotPacket(byte windowID, short slot, ItemStack itemData) {
        this.windowID = windowID;
        this.slot = slot;
        this.itemData = itemData;
    }

    public byte getWindowID() {
        return windowID;
    }

    public short getSlot() {
        return slot;
    }

    public ItemStack getSlotData() {
        return itemData;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.SET_SLOT;
    }
}
