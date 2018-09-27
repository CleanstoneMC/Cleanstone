package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.outbound.SpawnPlayerPacket;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

@Component
public class SpawnPlayerCodec implements OutboundPacketCodec<SpawnPlayerPacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, SpawnPlayerPacket packet) {

        ByteBufUtils.writeVarInt(byteBuf, packet.getEntityID());
        ByteBufUtils.writeUUID(byteBuf, packet.getPlayerUUID());
        byteBuf.writeDouble(packet.getPosition().getX());
        byteBuf.writeDouble(packet.getPosition().getY());
        byteBuf.writeDouble(packet.getPosition().getZ());
        byteBuf.writeByte((int) packet.getPosition().getRotation().getPitch());
        byteBuf.writeByte((int) packet.getPosition().getRotation().getYaw());

        byteBuf.writeByte(0xff);
        //TODO: Write EntityMetadata

        return byteBuf;
    }
}
