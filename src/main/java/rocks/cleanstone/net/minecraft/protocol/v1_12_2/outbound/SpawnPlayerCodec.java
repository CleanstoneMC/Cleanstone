package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.minecraft.packet.outbound.SpawnPlayerPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.SpawnPositionPacket;
import rocks.cleanstone.net.minecraft.protocol.MinecraftPacketCodec;
import rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.protocol.ProtocolState;
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
        byteBuf.writeDouble(spawnPlayerPacket.getX());
        byteBuf.writeDouble(spawnPlayerPacket.getY());
        byteBuf.writeDouble(spawnPlayerPacket.getZ());
        byteBuf.writeByte((int) spawnPlayerPacket.getPitch());
        byteBuf.writeByte((int) spawnPlayerPacket.getYaw());
        //TODO: Write Entitymetadata


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
