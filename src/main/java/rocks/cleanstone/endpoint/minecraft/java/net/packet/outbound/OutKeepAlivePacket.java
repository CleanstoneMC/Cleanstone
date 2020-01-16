package rocks.cleanstone.endpoint.minecraft.java.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.java.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class OutKeepAlivePacket implements Packet {

    private final long keepAliveID;

    public OutKeepAlivePacket(long keepAliveID) {
        this.keepAliveID = keepAliveID;
    }

    public long getKeepAliveID() {
        return keepAliveID;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.KEEP_ALIVE;
    }
}
