package rocks.cleanstone.net.packet.cleanstone.receive;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.ReceivePacket;
import rocks.cleanstone.net.packet.cleanstone.CleanstoneReceivePacketType;

public class KeepAlive extends ReceivePacket {
    @Override
    public PacketType getType() {
        return CleanstoneReceivePacketType.KEEP_ALIVE;
    }
}
