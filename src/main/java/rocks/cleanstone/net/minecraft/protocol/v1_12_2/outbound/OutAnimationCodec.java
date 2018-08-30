package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.outbound.AnimationPacket;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class OutAnimationCodec implements PacketCodec {

    @Override
    public Packet decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("OutAnimation is outbound and cannot be decoded");
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        AnimationPacket animationPacket = (AnimationPacket) packet;

        ByteBufUtils.writeVarInt(byteBuf, animationPacket.getEntityID());
        byteBuf.writeByte(animationPacket.getAnimation().getAnimationID());

        return byteBuf;
    }
}
