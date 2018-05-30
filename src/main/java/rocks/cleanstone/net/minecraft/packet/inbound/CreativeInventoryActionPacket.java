package rocks.cleanstone.net.minecraft.packet.inbound;

import rocks.cleanstone.net.minecraft.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.minecraft.packet.data.Slot;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class CreativeInventoryActionPacket implements Packet {

    private final short slot;
    private final Slot clickedItem;

    public CreativeInventoryActionPacket(short slot, Slot clickedItem) {
        this.slot = slot;
        this.clickedItem = clickedItem;
    }

    public short getSlot() {
        return slot;
    }

    public Slot getClickedItem() {
        return clickedItem;
    }

    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.CREATIVE_INVENTORY_ACTION;
    }
}
