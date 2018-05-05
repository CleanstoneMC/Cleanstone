package rocks.cleanstone.net.cleanstone.packet.inbound;

import rocks.cleanstone.net.cleanstone.packet.CleanstoneInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class KeepAlive implements Packet {
    @Override
    public PacketType getType() {
        return CleanstoneInboundPacketType.KEEP_ALIVE;
    }
}
