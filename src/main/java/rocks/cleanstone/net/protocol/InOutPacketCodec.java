package rocks.cleanstone.net.protocol;

import rocks.cleanstone.net.packet.Packet;

public interface InOutPacketCodec<T extends Packet> extends InboundPacketCodec<T>, OutboundPacketCodec<T> {
}
