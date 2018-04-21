package rocks.cleanstone.net.packet.minecraft.outbound;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftOutboundPacketType;

public class CloseWindowPacket implements Packet {

    private final byte windowID;

    public CloseWindowPacket(byte windowID) {
        this.windowID = windowID;
    }

    public byte getWindowID() {
        return windowID;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.CLOSE_WINDOW;
    }
}
