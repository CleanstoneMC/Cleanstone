package rocks.cleanstone.net.packet.cleanstone.outbound;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.cleanstone.CleanstoneOutboundPacketType;

public class KeepAlive implements Packet {
    @Override
    public PacketType getType() {
        return CleanstoneOutboundPacketType.KEEP_ALIVE;
    }
}
