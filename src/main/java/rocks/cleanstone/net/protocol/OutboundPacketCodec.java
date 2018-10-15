package rocks.cleanstone.net.protocol;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.packet.Packet;

import java.io.IOException;

public interface OutboundPacketCodec<T extends Packet> extends PacketCodec<T> {
    ByteBuf encode(ByteBuf byteBuf, T packet) throws IOException;
}
