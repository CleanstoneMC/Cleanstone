package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.minecraft.packet.inbound.InAnimationPacket;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

public class InAnimationCodec implements PacketCodec {

    @Override
    public Packet decode(ByteBuf byteBuf) throws IOException {
        final int hand = ByteBufUtils.readVarInt(byteBuf);

        return new InAnimationPacket(hand);
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        throw new UnsupportedOperationException("InAnimation is inbound and cannot be encoded");
    }
}
