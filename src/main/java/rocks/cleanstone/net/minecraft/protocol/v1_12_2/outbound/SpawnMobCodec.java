package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import org.springframework.stereotype.Component;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.minecraft.packet.outbound.SpawnMobPacket;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

@Component
public class SpawnMobCodec implements OutboundPacketCodec<SpawnMobPacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, SpawnMobPacket packet) {

        ByteBufUtils.writeVarInt(byteBuf, packet.getEntityID());
        ByteBufUtils.writeUUID(byteBuf, packet.getEntityUUID());
        ByteBufUtils.writeVarInt(byteBuf, packet.getVanillaEntity().getEntityType().getTypeID());
        byteBuf.writeDouble(packet.getX());
        byteBuf.writeDouble(packet.getY());
        byteBuf.writeDouble(packet.getZ());
        byteBuf.writeByte((int) packet.getYaw());
        byteBuf.writeByte((int) packet.getPitch());
        byteBuf.writeByte((int) packet.getHeadPitch());
        byteBuf.writeShort(packet.getVelocityX());
        byteBuf.writeShort(packet.getVelocityY());
        byteBuf.writeShort(packet.getVelocityZ());

        packet.getVanillaEntity().getEntityMetadata().getMetadataEntries().forEach((entityMetadataEntry -> {
            byteBuf.writeByte(entityMetadataEntry.getIndex());
            ByteBufUtils.writeVarInt(byteBuf, entityMetadataEntry.getType().getTypeID());
            ByteBuf valueData = entityMetadataEntry.getData().serialize();
            byteBuf.writeBytes(valueData);
            valueData.release();
        }));
        // Terminating metadata entry
        byteBuf.writeByte(0xff);

        return byteBuf;
    }
}
