package rocks.cleanstone.net.minecraft.packet.outbound;

import rocks.cleanstone.net.minecraft.packet.MinecraftOutboundPacketType;
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
