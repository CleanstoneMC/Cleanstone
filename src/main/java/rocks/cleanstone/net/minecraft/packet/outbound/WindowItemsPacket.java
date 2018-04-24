package rocks.cleanstone.net.minecraft.packet.outbound;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.minecraft.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.minecraft.packet.data.Slot;

import java.util.List;

public class WindowItemsPacket implements Packet {

    private final byte windowID;
    private final List<Slot> slots;

    public WindowItemsPacket(byte windowID, List<Slot> slots) {
        this.windowID = windowID;
        this.slots = slots;
    }

    public byte getWindowID() {
        return windowID;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.WINDOW_ITEMS;
    }
}
