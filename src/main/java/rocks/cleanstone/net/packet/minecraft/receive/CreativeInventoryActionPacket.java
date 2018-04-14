package rocks.cleanstone.net.packet.minecraft.receive;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.ReceivePacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftReceivePacketType;
import rocks.cleanstone.net.packet.minecraft.data.Slot;

public class CreativeInventoryActionPacket extends ReceivePacket {

    private final short slot;
    private final Slot clickedItem;

    public CreativeInventoryActionPacket(short slot, Slot clickedItem) {
        this.slot = slot;
        this.clickedItem = clickedItem;
    }

    public short getSlot() {
        return slot;
    }

    @Override
    public PacketType getType() {
        return MinecraftReceivePacketType.CREATIVE_INVENTORY_ACTION;
    }
}
