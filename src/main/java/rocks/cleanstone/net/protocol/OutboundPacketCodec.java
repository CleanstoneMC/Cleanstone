package rocks.cleanstone.net.protocol;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import rocks.cleanstone.net.packet.Packet;

public interface OutboundPacketCodec<T extends Packet> extends PacketCodec<T> {
    ByteBuf encode(ByteBuf byteBuf, T packet) throws IOException;
}
