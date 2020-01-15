package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.minecraft.packet.inbound.HandshakePacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.InboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Codec
public class HandshakeCodec implements InboundPacketCodec<HandshakePacket> {

    @Override
    public HandshakePacket decode(ByteBuf byteBuf) throws IOException {
        final int version = ByteBufUtils.readVarInt(byteBuf);
        final String address = ByteBufUtils.readUTF8(byteBuf);
        final int port = byteBuf.readUnsignedShort();
        final int state = ByteBufUtils.readVarInt(byteBuf);

        return new HandshakePacket(version, address, port, state);
    }
}
