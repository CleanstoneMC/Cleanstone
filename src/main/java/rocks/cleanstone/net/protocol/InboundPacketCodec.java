package rocks.cleanstone.net.protocol;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import rocks.cleanstone.net.packet.Packet;

public interface InboundPacketCodec<T extends Packet> extends PacketCodec<T> {
    T decode(ByteBuf byteBuf) throws IOException;
}
