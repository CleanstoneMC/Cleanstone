package rocks.cleanstone.net.cleanstone.packet.outbound;

import rocks.cleanstone.net.cleanstone.packet.CleanstoneOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class KeepAlive implements Packet {
    @Override
    public PacketType getType() {
        return CleanstoneOutboundPacketType.KEEP_ALIVE;
    }
}
