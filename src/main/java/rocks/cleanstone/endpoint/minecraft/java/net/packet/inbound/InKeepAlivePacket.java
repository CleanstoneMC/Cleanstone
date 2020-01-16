package rocks.cleanstone.endpoint.minecraft.java.net.packet.inbound;

import rocks.cleanstone.endpoint.minecraft.java.net.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class InKeepAlivePacket implements Packet {

    private final long keepAliveID;

    public InKeepAlivePacket(long keepAliveID) {
        this.keepAliveID = keepAliveID;
    }

    public long getKeepAliveID() {
        return keepAliveID;
    }

    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.KEEP_ALIVE;
    }
}
