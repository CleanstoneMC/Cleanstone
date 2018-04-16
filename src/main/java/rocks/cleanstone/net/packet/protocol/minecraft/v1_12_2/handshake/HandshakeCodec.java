package rocks.cleanstone.net.packet.protocol.minecraft.v1_12_2.handshake;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.minecraft.inbound.HandshakePacket;
import rocks.cleanstone.net.packet.protocol.minecraft.MinecraftPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class HandshakeCodec implements MinecraftPacketCodec {

    @Override
    public Packet decode(ByteBuf byteBuf) throws IOException {
        final int version = ByteBufUtils.readVarInt(byteBuf);
        final String address = ByteBufUtils.readUTF8(byteBuf);
        final int port = byteBuf.readUnsignedShort();
        final int state = ByteBufUtils.readVarInt(byteBuf);

        return new HandshakePacket(version, address, port, state);
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) throws IOException {
        HandshakePacket handshakePacket = (HandshakePacket) packet;

        ByteBufUtils.writeVarInt(byteBuf, handshakePacket.getVersion());
        ByteBufUtils.writeUTF8(byteBuf, handshakePacket.getAddress());
        byteBuf.writeShort(handshakePacket.getPort());
        ByteBufUtils.writeVarInt(byteBuf, handshakePacket.getState());

        return byteBuf;
    }

    @Override
    public ByteBuf downgradeByteBuf(ByteBuf nextLayerByteBuf) {
        return nextLayerByteBuf;
    }

    @Override
    public Packet upgradePOJO(Packet previousLayerPacket) {
        return null;
    }

    @Override
    public int getProtocolPacketID() {
        return 0x00;
    }
}
