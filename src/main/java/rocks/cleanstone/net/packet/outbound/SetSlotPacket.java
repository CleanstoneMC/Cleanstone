package rocks.cleanstone.net.packet.outbound;

import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.data.Slot;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class SetSlotPacket implements Packet {

    private final byte windowID;
    private final short slot;
    private final Slot slotData;

    public SetSlotPacket(byte windowID, short slot, Slot slotData) {
        this.windowID = windowID;
        this.slot = slot;
        this.slotData = slotData;
    }

    public byte getWindowID() {
        return windowID;
    }

    public short getSlot() {
        return slot;
    }

    public Slot getSlotData() {
        return slotData;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.SET_SLOT;
    }
}
