package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.minecraft.packet.outbound.TimeUpdatePacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;

@Codec
public class TimeUpdateCodec implements OutboundPacketCodec<TimeUpdatePacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, TimeUpdatePacket packet) {

        byteBuf.writeLong(packet.getWorldAge());
        byteBuf.writeLong(packet.getTimeOfDay());

        return byteBuf;
    }
}
