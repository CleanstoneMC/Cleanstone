package rocks.cleanstone.net.packet.minecraft.inbound;

import rocks.cleanstone.net.packet.InboundPacket;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftInboundPacketType;

public class KeepAlivePacket extends InboundPacket {

    private final long keepAliveID;

    public KeepAlivePacket(long keepAliveID) {
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
