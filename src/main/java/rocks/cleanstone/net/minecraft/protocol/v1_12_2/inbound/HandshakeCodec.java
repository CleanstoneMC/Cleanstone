package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.inbound.HandshakePacket;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Component
public class HandshakeCodec implements PacketCodec<HandshakePacket> {

    @Override
    public HandshakePacket decode(ByteBuf byteBuf) throws IOException {
        final int version = ByteBufUtils.readVarInt(byteBuf);
        final String address = ByteBufUtils.readUTF8(byteBuf);
        final int port = byteBuf.readUnsignedShort();
        final int state = ByteBufUtils.readVarInt(byteBuf);

        return new HandshakePacket(version, address, port, state);
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, HandshakePacket packet) {
        throw new UnsupportedOperationException("Handshake is inbound and cannot be encoded");
    }
}
