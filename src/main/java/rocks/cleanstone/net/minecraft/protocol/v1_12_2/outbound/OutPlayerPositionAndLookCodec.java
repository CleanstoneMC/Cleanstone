package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.outbound.OutPlayerPositionAndLookPacket;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

public class OutPlayerPositionAndLookCodec implements PacketCodec {

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
}
