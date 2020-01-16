package rocks.cleanstone.endpoint.minecraft.java.v1_12_2.net.protocol.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.endpoint.minecraft.java.net.packet.outbound.PongPacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;

@Codec
public class PongCodec implements OutboundPacketCodec<PongPacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, PongPacket packet) {

        byteBuf.writeLong(packet.getPayload());
        return byteBuf;
    }
}
