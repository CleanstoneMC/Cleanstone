package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.outbound.EntityRelativeMovePacket;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class EntityRelativeMoveCodec implements PacketCodec {

    @Override
    public Packet decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("EntityRelativeMove is outbound and cannot be decoded");
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        EntityRelativeMovePacket entityRelativeMovePacket = (EntityRelativeMovePacket) packet;

        ByteBufUtils.writeVarInt(byteBuf, entityRelativeMovePacket.getEntityID());
        byteBuf.writeShort(entityRelativeMovePacket.getDeltaX());
        byteBuf.writeShort(entityRelativeMovePacket.getDeltaY());
        byteBuf.writeShort(entityRelativeMovePacket.getDeltaZ());
        byteBuf.writeBoolean(entityRelativeMovePacket.isOnGround());

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
