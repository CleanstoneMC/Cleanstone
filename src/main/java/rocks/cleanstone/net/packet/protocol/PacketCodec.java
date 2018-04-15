package rocks.cleanstone.net.packet.protocol;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.packet.Packet;

public interface PacketCodec<T extends Packet> {
    T decode(ByteBuf byteBuf) throws IOException;

    ByteBuf encode(ByteBuf byteBuf, T packet) throws IOException;

    ByteBuf downgradeByteBuf(ByteBuf nextLayerByteBuf);

    Packet upgradePOJO(Packet previousLayerPacket);
}
