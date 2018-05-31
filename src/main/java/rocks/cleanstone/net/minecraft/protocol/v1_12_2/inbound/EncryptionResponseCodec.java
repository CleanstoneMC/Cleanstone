package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.packet.inbound.EncryptionResponsePacket;
import rocks.cleanstone.net.minecraft.protocol.MinecraftPacketCodec;
import rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.protocol.ProtocolState;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class EncryptionResponseCodec implements MinecraftPacketCodec {

    @Override
    public Packet decode(ByteBuf byteBuf) throws IOException {
        int publicKeyLength = ByteBufUtils.readVarInt(byteBuf);
        byte[] publicKeyArray = new byte[publicKeyLength];
        byteBuf.readBytes(publicKeyArray);
        int verifyTokenLength = ByteBufUtils.readVarInt(byteBuf);
        byte[] verifyTokenArray = new byte[verifyTokenLength];
        byteBuf.readBytes(verifyTokenArray);
        return new EncryptionResponsePacket(publicKeyArray, verifyTokenArray);
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        throw new UnsupportedOperationException("EncryptionResponse is inbound and cannot be encoded");
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
