package rocks.cleanstone.endpoint.minecraft.vanilla.v1_12_2.net.protocol.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound.EncryptionRequestPacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Codec
public class EncryptionRequestCodec implements OutboundPacketCodec<EncryptionRequestPacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, EncryptionRequestPacket packet) throws IOException {

        ByteBufUtils.writeUTF8(byteBuf, packet.getServerID());
        ByteBufUtils.writeVarInt(byteBuf, packet.getPublicKey().length);
        byteBuf.writeBytes(packet.getPublicKey());
        ByteBufUtils.writeVarInt(byteBuf, packet.getVerifyToken().length);
        byteBuf.writeBytes(packet.getVerifyToken());
        return byteBuf;
    }
}
