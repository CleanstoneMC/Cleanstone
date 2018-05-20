package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.minecraft.packet.outbound.EntityRelativeMovePacket;
import rocks.cleanstone.net.minecraft.protocol.MinecraftPacketCodec;
import rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.protocol.ProtocolState;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class EntityRelativeMoveCodec implements MinecraftPacketCodec {

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

    @Override
    public int getProtocolPacketID() {
        return 0x26;
    }

    @Override
    public ProtocolState getProtocolState() {
        return VanillaProtocolState.PLAY;
    }
}
