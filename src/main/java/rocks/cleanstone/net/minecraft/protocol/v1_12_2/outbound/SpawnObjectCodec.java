package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.outbound.SpawnObjectPacket;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

@Component
public class SpawnObjectCodec implements OutboundPacketCodec<SpawnObjectPacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, SpawnObjectPacket packet) {

        ByteBufUtils.writeVarInt(byteBuf, packet.getEntityID());
        ByteBufUtils.writeUUID(byteBuf, packet.getObjectUUID());
        //byteBuf.writeByte(spawnObjectPacket.getType().getTypeID());
        byteBuf.writeDouble(packet.getX());
        byteBuf.writeDouble(packet.getY());
        byteBuf.writeDouble(packet.getZ());
        byteBuf.writeByte((int) packet.getPitch());
        byteBuf.writeByte((int) packet.getPitch());
        byteBuf.writeInt(packet.getData());
        byteBuf.writeShort(packet.getVelocityX());
        byteBuf.writeShort(packet.getVelocityY());
        byteBuf.writeShort(packet.getVelocityZ());

        return byteBuf;
    }
}
