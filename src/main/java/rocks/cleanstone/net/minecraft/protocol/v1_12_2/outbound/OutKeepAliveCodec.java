package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.minecraft.packet.outbound.OutKeepAlivePacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;

@Codec
public class OutKeepAliveCodec implements OutboundPacketCodec<OutKeepAlivePacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, OutKeepAlivePacket packet) {

        byteBuf.writeLong(packet.getKeepAliveID());
        return byteBuf;
    }
}
