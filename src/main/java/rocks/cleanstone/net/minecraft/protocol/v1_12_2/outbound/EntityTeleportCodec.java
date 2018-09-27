package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.outbound.EntityTeleportPacket;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

@Component
public class EntityTeleportCodec implements OutboundPacketCodec<EntityTeleportPacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, EntityTeleportPacket packet) {

        ByteBufUtils.writeVarInt(byteBuf, packet.getEntityID());
        byteBuf.writeDouble(packet.getX());
        byteBuf.writeDouble(packet.getY());
        byteBuf.writeDouble(packet.getZ());
        byteBuf.writeByte(packet.getYaw());
        byteBuf.writeByte(packet.getPitch());
        byteBuf.writeBoolean(packet.isOnGround());

        return byteBuf;
    }
}
