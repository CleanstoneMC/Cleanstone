package rocks.cleanstone.net.packet.cleanstone.send;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.SendPacket;
import rocks.cleanstone.net.packet.cleanstone.CleanstoneSendPacketType;

public class KeepAlive extends SendPacket {
    @Override
    public PacketType getType() {
        return CleanstoneSendPacketType.KEEP_ALIVE;
    }
}
