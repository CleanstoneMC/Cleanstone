package rocks.cleanstone.endpoint.minecraft.vanilla.legacy.v1_12_2.net.protocol.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound.EntityHeadLookPacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

@Codec
public class EntityHeadLookCodec implements OutboundPacketCodec<EntityHeadLookPacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, EntityHeadLookPacket packet) {

        ByteBufUtils.writeVarInt(byteBuf, packet.getEntityID());
        byteBuf.writeByte((int) (packet.getYaw() / 360.0 * 256));

        return byteBuf;
    }
}
