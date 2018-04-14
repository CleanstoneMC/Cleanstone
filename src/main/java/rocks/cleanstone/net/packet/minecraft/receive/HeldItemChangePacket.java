package rocks.cleanstone.net.packet.minecraft.receive;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.ReceivePacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftReceivePacketType;

public class HeldItemChangePacket extends ReceivePacket {

    private final short slot;

    public HeldItemChangePacket(short slot) {
        this.slot = slot;
    }

    public short getSlot() {
        return slot;
    }

    @Override
    public PacketType getType() {
        return MinecraftReceivePacketType.HELD_ITEM_CHANGE;
    }
}
