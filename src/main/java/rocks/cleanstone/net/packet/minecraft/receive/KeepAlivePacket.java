package rocks.cleanstone.net.packet.minecraft.receive;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.ReceivePacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftReceivePacketType;

public class KeepAlivePacket extends ReceivePacket {

    private final long keepAliveID;

    public KeepAlivePacket(long keepAliveID) {
        this.keepAliveID = keepAliveID;
    }

    public long getKeepAliveID() {
        return keepAliveID;
    }

    @Override
    public PacketType getType() {
        return MinecraftReceivePacketType.KEEP_ALIVE;
    }
}
