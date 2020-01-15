package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.minecraft.packet.outbound.SetCompressionPacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

@Codec
public class SetCompressionCodec implements OutboundPacketCodec<SetCompressionPacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, SetCompressionPacket packet) {

        ByteBufUtils.writeVarInt(byteBuf, packet.getThreshold());
        return byteBuf;
    }
}
