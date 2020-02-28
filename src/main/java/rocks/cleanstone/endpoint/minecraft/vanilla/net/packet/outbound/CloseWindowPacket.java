package rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.MinecraftOutboundPacketType;
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
        return MinecraftOutboundPacketType.CLOSE_WINDOW;
    }
}
