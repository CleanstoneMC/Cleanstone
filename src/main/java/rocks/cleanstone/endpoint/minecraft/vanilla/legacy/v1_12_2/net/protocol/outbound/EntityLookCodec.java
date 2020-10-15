package rocks.cleanstone.endpoint.minecraft.vanilla.legacy.v1_12_2.net.protocol.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound.EntityLookPacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

@Codec
public class EntityLookCodec implements OutboundPacketCodec<EntityLookPacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, EntityLookPacket packet) {

        ByteBufUtils.writeVarInt(byteBuf, packet.getEntityID());
        byteBuf.writeByte((int) (packet.getYaw() / 360.0 * 256));
        byteBuf.writeByte((int) (packet.getPitch() / 360.0 * 256));
        byteBuf.writeBoolean(packet.isOnGround());

        return byteBuf;
    }
}
