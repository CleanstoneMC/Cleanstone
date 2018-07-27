package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.inbound.InKeepAlivePacket;
import rocks.cleanstone.net.protocol.PacketCodec;

public class InKeepAliveCodec implements PacketCodec {

    @Override
    public Packet decode(ByteBuf byteBuf) {
        final long keepAliveID = byteBuf.readLong();

        return new InKeepAlivePacket(keepAliveID);
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        throw new UnsupportedOperationException("KeepAlive is inbound and cannot be encoded");
    }

    @Override
    public ByteBuf upgradeByteBuf(ByteBuf previousLayerByteBuf) {
        return previousLayerByteBuf;
    }

    @Override
    public ByteBuf downgradeByteBuf(ByteBuf nextLayerByteBuf) {
        return nextLayerByteBuf;
    }
}
