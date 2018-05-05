package rocks.cleanstone.net.minecraft.packet.inbound;

import rocks.cleanstone.net.minecraft.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

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
        return MinecraftInboundPacketType.CLOSE_WINDOW;
    }
}
