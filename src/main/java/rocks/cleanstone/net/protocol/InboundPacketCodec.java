package rocks.cleanstone.net.protocol;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.packet.Packet;

import java.io.IOException;

public interface InboundPacketCodec<T extends Packet> extends PacketCodec<T> {
    T decode(ByteBuf byteBuf) throws IOException;
}
