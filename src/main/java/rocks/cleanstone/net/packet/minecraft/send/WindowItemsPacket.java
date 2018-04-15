package rocks.cleanstone.net.packet.minecraft.send;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.SendPacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftSendPacketType;
import rocks.cleanstone.net.packet.minecraft.data.Slot;

import java.util.List;

public class WindowItemsPacket extends SendPacket {

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
        return MinecraftSendPacketType.WINDOW_ITEMS;
    }
}
