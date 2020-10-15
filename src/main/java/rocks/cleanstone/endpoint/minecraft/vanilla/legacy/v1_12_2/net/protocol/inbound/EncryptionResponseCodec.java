package rocks.cleanstone.endpoint.minecraft.vanilla.legacy.v1_12_2.net.protocol.inbound;

import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.inbound.EncryptionResponsePacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.InboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Codec
public class EncryptionResponseCodec implements InboundPacketCodec<EncryptionResponsePacket> {

    @Override
    public EncryptionResponsePacket decode(ByteBuf byteBuf) throws IOException {
        int publicKeyLength = ByteBufUtils.readVarInt(byteBuf);
        Preconditions.checkArgument(publicKeyLength <= 256 && publicKeyLength > 0,
                "publicKeyLength " + publicKeyLength + " is too big/small");

        byte[] publicKeyArray = new byte[publicKeyLength];
        byteBuf.readBytes(publicKeyArray);

        int verifyTokenLength = ByteBufUtils.readVarInt(byteBuf);
        Preconditions.checkArgument(verifyTokenLength <= 256 && verifyTokenLength > 0,
                "verifyTokenLength " + verifyTokenLength + " is too big/small");

        byte[] verifyTokenArray = new byte[verifyTokenLength];
        byteBuf.readBytes(verifyTokenArray);

        return new EncryptionResponsePacket(publicKeyArray, verifyTokenArray);
    }
}
