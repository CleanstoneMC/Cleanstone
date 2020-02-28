package rocks.cleanstone.endpoint.minecraft.vanilla.v1_12_2.net.protocol.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound.ChangeGameStatePacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;

@Codec
public class ChangeGameStateCodec implements OutboundPacketCodec<ChangeGameStatePacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, ChangeGameStatePacket packet) {

        byteBuf.writeByte(packet.getReason().getReasonID());
        byteBuf.writeFloat(packet.getValue());

        return byteBuf;
    }
}
