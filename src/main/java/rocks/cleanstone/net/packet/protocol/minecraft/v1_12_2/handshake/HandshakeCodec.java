package rocks.cleanstone.net.packet.protocol.minecraft.v1_12_2.handshake;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.minecraft.inbound.HandshakePacket;
import rocks.cleanstone.net.packet.protocol.minecraft.MinecraftPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class HandshakeCodec implements MinecraftPacketCodec<HandshakePacket> {

    @Override
    public HandshakePacket decode(ByteBuf byteBuf) throws IOException {
        final int version = ByteBufUtils.readVarInt(byteBuf);
        final String address = ByteBufUtils.readUTF8(byteBuf);
        final int port = byteBuf.readUnsignedShort();
        final int state = ByteBufUtils.readVarInt(byteBuf);

        return new HandshakePacket(version, address, port, state);
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, HandshakePacket packet) throws IOException {
        ByteBufUtils.writeVarInt(byteBuf, packet.getVersion());
        ByteBufUtils.writeUTF8(byteBuf, packet.getAddress());
        byteBuf.writeShort(packet.getPort());
        ByteBufUtils.writeVarInt(byteBuf, packet.getState());

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
    public int getProtocolPacketId() {
        return 0x00;
    }
}
