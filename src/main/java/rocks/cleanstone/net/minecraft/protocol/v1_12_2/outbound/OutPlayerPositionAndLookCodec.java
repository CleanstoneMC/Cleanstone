package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.minecraft.protocol.MinecraftPacketCodec;
import rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.outbound.OutPlayerPositionAndLookPacket;
import rocks.cleanstone.net.protocol.ProtocolState;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class OutPlayerPositionAndLookCodec implements MinecraftPacketCodec {

    @Override
    public Packet decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("PlayerPositionAndLook is outbound and cannot be decoded");
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) throws IOException {
        OutPlayerPositionAndLookPacket playerPositionAndLookPacket = (OutPlayerPositionAndLookPacket) packet;

        byteBuf.writeDouble(playerPositionAndLookPacket.getPosition().getX());
        byteBuf.writeDouble(playerPositionAndLookPacket.getPosition().getY());
        byteBuf.writeDouble(playerPositionAndLookPacket.getPosition().getZ());
        byteBuf.writeFloat(playerPositionAndLookPacket.getPosition().getRotation().getYaw());
        byteBuf.writeFloat(playerPositionAndLookPacket.getPosition().getRotation().getPitch());
        byteBuf.writeByte(playerPositionAndLookPacket.getFlags());
        ByteBufUtils.writeVarInt(byteBuf, playerPositionAndLookPacket.getTeleportID());

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
        return 0x2F;
    }

    @Override
    public ProtocolState getProtocolState() {
        return VanillaProtocolState.PLAY;
    }
}
