package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.outbound.EncryptionRequestPacket;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

public class EncryptionRequestCodec implements PacketCodec {

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
}
