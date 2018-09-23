package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.outbound.SpawnMobPacket;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

@Component
public class SpawnMobCodec implements PacketCodec<SpawnMobPacket> {

    @Override
    public SpawnMobPacket decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("SpawnMob is outbound and cannot be decoded");
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, SpawnMobPacket packet) {

        ByteBufUtils.writeVarInt(byteBuf, packet.getEntityID());
        ByteBufUtils.writeUUID(byteBuf, packet.getEntityUUID());
        ByteBufUtils.writeVarInt(byteBuf, packet.getMobType().getTypeID());
        byteBuf.writeDouble(packet.getX());
        byteBuf.writeDouble(packet.getY());
        byteBuf.writeDouble(packet.getZ());
        byteBuf.writeByte((int) packet.getYaw());
        byteBuf.writeByte((int) packet.getPitch());
        byteBuf.writeByte((int) packet.getHeadPitch());
        byteBuf.writeShort(packet.getVelocityX());
        byteBuf.writeShort(packet.getVelocityY());
        byteBuf.writeShort(packet.getVelocityZ());

        byteBuf.writeByte(0xff);
        //TODO: Write EntityMetadata

        return byteBuf;
    }
}
