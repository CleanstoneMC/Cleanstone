package rocks.cleanstone.net.packet;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

public interface PacketFactory<T> {
    T decode(ByteBuf byteBuf) throws IOException;

    ByteBuf encode(ByteBuf byteBuf, T packet) throws IOException;
}
