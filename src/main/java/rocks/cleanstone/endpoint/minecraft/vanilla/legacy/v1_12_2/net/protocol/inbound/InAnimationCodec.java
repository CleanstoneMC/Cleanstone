package rocks.cleanstone.endpoint.minecraft.vanilla.legacy.v1_12_2.net.protocol.inbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.inbound.InAnimationPacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.InboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Codec
public class InAnimationCodec implements InboundPacketCodec<InAnimationPacket> {

    @Override
    public InAnimationPacket decode(ByteBuf byteBuf) throws IOException {
        final int hand = ByteBufUtils.readVarInt(byteBuf);

        return new InAnimationPacket(hand);
    }
}
