package rocks.cleanstone.net.packet.protocol.minecraft.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.minecraft.outbound.EncryptionRequestPacket;
import rocks.cleanstone.net.packet.protocol.ProtocolState;
import rocks.cleanstone.net.packet.protocol.minecraft.MinecraftPacketCodec;
import rocks.cleanstone.net.packet.protocol.minecraft.VanillaProtocolState;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

public class EncryptionRequestCodec implements MinecraftPacketCodec {

    @Override
    public Packet decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("EncryptionRequest is outbound and cannot be decoded");
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) throws IOException {
        EncryptionRequestPacket encryptionRequestPacket = (EncryptionRequestPacket) packet;

        ByteBufUtils.writeUTF8(byteBuf, encryptionRequestPacket.getServerID());
        ByteBufUtils.writeVarInt(byteBuf, encryptionRequestPacket.getPublicKey().length);
        byteBuf.writeBytes(encryptionRequestPacket.getPublicKey());
        ByteBufUtils.writeVarInt(byteBuf, encryptionRequestPacket.getVerifyToken().length);
        byteBuf.writeBytes(encryptionRequestPacket.getVerifyToken());
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
        return 0x01;
    }

    @Override
    public ProtocolState getProtocolState() {
        return VanillaProtocolState.LOGIN;
    }
}
