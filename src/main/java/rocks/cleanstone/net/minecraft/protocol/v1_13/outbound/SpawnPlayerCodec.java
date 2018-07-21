package rocks.cleanstone.net.minecraft.protocol.v1_13.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.minecraft.protocol.MinecraftPacketCodec;
import rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.outbound.SpawnPlayerPacket;
import rocks.cleanstone.net.protocol.ProtocolState;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class SpawnPlayerCodec implements MinecraftPacketCodec {

    @Override
    public Packet decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("SpawnPlayer is outbound and cannot be decoded");
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        SpawnPlayerPacket spawnPlayerPacket = (SpawnPlayerPacket) packet;

        ByteBufUtils.writeVarInt(byteBuf, spawnPlayerPacket.getEntityID());
        ByteBufUtils.writeUUID(byteBuf, spawnPlayerPacket.getPlayerUUID());
        byteBuf.writeDouble(spawnPlayerPacket.getPosition().getX());
        byteBuf.writeDouble(spawnPlayerPacket.getPosition().getY());
        byteBuf.writeDouble(spawnPlayerPacket.getPosition().getZ());
        byteBuf.writeByte((int) spawnPlayerPacket.getPosition().getRotation().getPitch());
        byteBuf.writeByte((int) spawnPlayerPacket.getPosition().getRotation().getYaw());

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

    @Override
    public int getProtocolPacketID() {
        return 0x05;
    }

    @Override
    public ProtocolState getProtocolState() {
        return VanillaProtocolState.PLAY;
    }
}
