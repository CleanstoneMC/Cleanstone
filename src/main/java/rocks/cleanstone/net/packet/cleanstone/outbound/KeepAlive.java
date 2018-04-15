package rocks.cleanstone.net.packet.cleanstone.outbound;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.OutboundPacket;
import rocks.cleanstone.net.packet.cleanstone.CleanstoneOutboundPacketType;

public class KeepAlive extends OutboundPacket {
    @Override
    public PacketType getType() {
        return CleanstoneOutboundPacketType.KEEP_ALIVE;
    }
}
