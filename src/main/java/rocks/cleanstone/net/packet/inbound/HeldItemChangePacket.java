package rocks.cleanstone.net.packet.inbound;

import rocks.cleanstone.net.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class HeldItemChangePacket implements Packet {

    private final short slot;

    public HeldItemChangePacket(short slot) {
        this.slot = slot;
    }

    public short getSlot() {
        return slot;
    }

    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.HELD_ITEM_CHANGE;
    }
}
