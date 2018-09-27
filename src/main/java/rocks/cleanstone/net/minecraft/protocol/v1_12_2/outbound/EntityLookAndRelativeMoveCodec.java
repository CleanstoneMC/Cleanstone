package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.outbound.EntityLookAndRelativeMovePacket;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

@Component
public class EntityLookAndRelativeMoveCodec implements OutboundPacketCodec<EntityLookAndRelativeMovePacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, EntityLookAndRelativeMovePacket packet) {

        ByteBufUtils.writeVarInt(byteBuf, packet.getEntityID());
        byteBuf.writeShort(packet.getDeltaX());
        byteBuf.writeShort(packet.getDeltaY());
        byteBuf.writeShort(packet.getDeltaZ());
        byteBuf.writeByte(packet.getYaw());
        byteBuf.writeByte(packet.getPitch());
        byteBuf.writeBoolean(packet.isOnGround());

        return byteBuf;
    }
}
