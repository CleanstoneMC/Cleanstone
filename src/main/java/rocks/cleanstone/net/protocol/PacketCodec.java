package rocks.cleanstone.net.protocol;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.packet.Packet;

import java.io.IOException;

public interface PacketCodec<T extends Packet> {
    T decode(ByteBuf byteBuf) throws IOException;

    ByteBuf encode(ByteBuf byteBuf, T packet) throws IOException;
}
