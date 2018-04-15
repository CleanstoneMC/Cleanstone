package rocks.cleanstone.net.packet.cleanstone.inbound;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.InboundPacket;
import rocks.cleanstone.net.packet.cleanstone.CleanstoneInboundPacketType;

public class KeepAlive extends InboundPacket {
    @Override
    public PacketType getType() {
        return CleanstoneInboundPacketType.KEEP_ALIVE;
    }
}
