package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.outbound.EntityTeleportPacket;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

public class EntityTeleportCodec implements PacketCodec {

    @Override
    public Packet decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("EntityTeleport is outbound and cannot be decoded");
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) throws IOException {
        EntityTeleportPacket entityTeleportPacket = (EntityTeleportPacket) packet;

        ByteBufUtils.writeVarInt(byteBuf, entityTeleportPacket.getEntityID());
        byteBuf.writeDouble(entityTeleportPacket.getX());
        byteBuf.writeDouble(entityTeleportPacket.getY());
        byteBuf.writeDouble(entityTeleportPacket.getZ());
        byteBuf.writeByte(entityTeleportPacket.getYaw());
        byteBuf.writeByte(entityTeleportPacket.getPitch());
        byteBuf.writeBoolean(entityTeleportPacket.isOnGround());

        return byteBuf;
    }

    @Override
    public ByteBuf upgradeByteBuf(ByteBuf previousLayerByteBuf) {
        return previousLayerByteBuf;
    }

    @Override
    public ByteBuf downgradeByteBuf(ByteBuf nextLayerByteBuf) {
        return nextLayerByteBuf;
    }
}
