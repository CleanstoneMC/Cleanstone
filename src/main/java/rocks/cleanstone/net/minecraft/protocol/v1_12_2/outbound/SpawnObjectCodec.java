package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.outbound.SpawnObjectPacket;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

public class SpawnObjectCodec implements PacketCodec {

    @Override
    public Packet decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("SpawnObject is outbound and cannot be decoded");
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) throws IOException {
        SpawnObjectPacket spawnObjectPacket = (SpawnObjectPacket) packet;

        ByteBufUtils.writeVarInt(byteBuf, spawnObjectPacket.getEntityID());
        ByteBufUtils.writeUUID(byteBuf, spawnObjectPacket.getObjectUUID());
        //byteBuf.writeByte(spawnObjectPacket.getType().getTypeID());
        byteBuf.writeDouble(spawnObjectPacket.getX());
        byteBuf.writeDouble(spawnObjectPacket.getY());
        byteBuf.writeDouble(spawnObjectPacket.getZ());
        byteBuf.writeByte((int) spawnObjectPacket.getPitch());
        byteBuf.writeByte((int) spawnObjectPacket.getPitch());
        byteBuf.writeInt(spawnObjectPacket.getData());
        byteBuf.writeShort(spawnObjectPacket.getVelocityX());
        byteBuf.writeShort(spawnObjectPacket.getVelocityY());
        byteBuf.writeShort(spawnObjectPacket.getVelocityZ());

        return byteBuf;
    }
}
