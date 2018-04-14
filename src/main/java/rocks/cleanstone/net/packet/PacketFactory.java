package rocks.cleanstone.net.packet;

import java.io.IOException;

import io.netty.buffer.ByteBuf;

public interface PacketFactory<T extends Packet> {
    T decode(ByteBuf byteBuf) throws IOException;

    ByteBuf encode(ByteBuf byteBuf, T packet) throws IOException;
}
