package rocks.cleanstone.endpoint.minecraft.vanilla.legacy.v1_12_2.net.protocol.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound.TimeUpdatePacket;
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
