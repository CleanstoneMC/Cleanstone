package rocks.cleanstone.endpoint.cleanstone.net.packet.inbound;

import rocks.cleanstone.endpoint.cleanstone.net.packet.CleanstoneInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class KeepAlive implements Packet {
    @Override
    public PacketType getType() {
        return CleanstoneInboundPacketType.KEEP_ALIVE;
    }
}
