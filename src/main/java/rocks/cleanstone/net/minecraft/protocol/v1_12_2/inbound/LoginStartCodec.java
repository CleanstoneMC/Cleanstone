package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.inbound.LoginStartPacket;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class LoginStartCodec implements PacketCodec {

    @Override
    public Packet decode(ByteBuf byteBuf) throws IOException {
        final String playerName = ByteBufUtils.readUTF8(byteBuf, 16);
        return new LoginStartPacket(playerName);
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        throw new UnsupportedOperationException("LoginStart is inbound and cannot be encoded");
    }
}
