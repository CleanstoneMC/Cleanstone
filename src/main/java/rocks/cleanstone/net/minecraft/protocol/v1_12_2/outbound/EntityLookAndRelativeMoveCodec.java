package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.minecraft.packet.outbound.EntityLookAndRelativeMovePacket;
import rocks.cleanstone.net.minecraft.packet.outbound.EntityLookPacket;
import rocks.cleanstone.net.minecraft.protocol.MinecraftPacketCodec;
import rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.protocol.ProtocolState;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class EntityLookAndRelativeMoveCodec implements MinecraftPacketCodec {

    @Override
    public Packet decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("EntityLook is outbound and cannot be decoded");
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        EntityLookAndRelativeMovePacket entityLookAndRelativeMovePacket = (EntityLookAndRelativeMovePacket) packet;

        ByteBufUtils.writeVarInt(byteBuf, entityLookAndRelativeMovePacket.getEntityID());
        byteBuf.writeShort(entityLookAndRelativeMovePacket.getDeltaX());
        byteBuf.writeShort(entityLookAndRelativeMovePacket.getDeltaY());
        byteBuf.writeShort(entityLookAndRelativeMovePacket.getDeltaZ());
        byteBuf.writeByte((int) entityLookAndRelativeMovePacket.getYaw());
        byteBuf.writeByte((int) entityLookAndRelativeMovePacket.getPitch());
        byteBuf.writeBoolean(entityLookAndRelativeMovePacket.isOnGround());

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
        return 0x27;
    }

    @Override
    public ProtocolState getProtocolState() {
        return VanillaProtocolState.PLAY;
    }
}
