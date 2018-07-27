package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.outbound.SpawnMobPacket;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class SpawnMobCodec implements PacketCodec {

    @Override
    public Packet decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("SpawnMob is outbound and cannot be decoded");
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        SpawnMobPacket spawnMobPacket = (SpawnMobPacket) packet;

        ByteBufUtils.writeVarInt(byteBuf, spawnMobPacket.getEntityID());
        ByteBufUtils.writeUUID(byteBuf, spawnMobPacket.getEntityUUID());
        ByteBufUtils.writeVarInt(byteBuf, spawnMobPacket.getMobType().getTypeID());
        byteBuf.writeDouble(spawnMobPacket.getX());
        byteBuf.writeDouble(spawnMobPacket.getY());
        byteBuf.writeDouble(spawnMobPacket.getZ());
        byteBuf.writeByte((int) spawnMobPacket.getYaw());
        byteBuf.writeByte((int) spawnMobPacket.getPitch());
        byteBuf.writeByte((int) spawnMobPacket.getHeadPitch());
        byteBuf.writeShort(spawnMobPacket.getVelocityX());
        byteBuf.writeShort(spawnMobPacket.getVelocityY());
        byteBuf.writeShort(spawnMobPacket.getVelocityZ());

        byteBuf.writeByte(0xff);
        //TODO: Write EntityMetadata

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
