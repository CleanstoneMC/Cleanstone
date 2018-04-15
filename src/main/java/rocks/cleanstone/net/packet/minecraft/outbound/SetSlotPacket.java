package rocks.cleanstone.net.packet.minecraft.outbound;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.OutboundPacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.minecraft.data.Slot;

public class SetSlotPacket extends OutboundPacket {

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
