package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.outbound.EncryptionRequestPacket;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Component
public class EncryptionRequestCodec implements PacketCodec<EncryptionRequestPacket> {

    @Override
    public EncryptionRequestPacket decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("EncryptionRequest is outbound and cannot be decoded");
    }

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
