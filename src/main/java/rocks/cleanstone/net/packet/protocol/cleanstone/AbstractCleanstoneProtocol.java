package rocks.cleanstone.net.packet.protocol.cleanstone;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketCodec;
import rocks.cleanstone.net.packet.protocol.Protocol;

public abstract class AbstractCleanstoneProtocol implements Protocol {

    @Override
    public <T extends Packet> PacketCodec<T> getPacketCodec(Class<T> packet) {
        return null;
    }
}
