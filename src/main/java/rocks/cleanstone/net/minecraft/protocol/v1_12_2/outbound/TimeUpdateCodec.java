package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.outbound.TimeUpdatePacket;
import rocks.cleanstone.net.protocol.PacketCodec;

public class TimeUpdateCodec implements PacketCodec {

    @Override
    public Packet decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("TimeUpdate is outbound and cannot be decoded");
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        TimeUpdatePacket timeUpdatePacket = (TimeUpdatePacket) packet;

        byteBuf.writeLong(timeUpdatePacket.getWorldAge());
        byteBuf.writeLong(timeUpdatePacket.getTimeOfDay());

        return byteBuf;
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
