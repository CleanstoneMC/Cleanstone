package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.minecraft.packet.inbound.LoginStartPacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.InboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Codec
public class LoginStartCodec implements InboundPacketCodec<LoginStartPacket> {
    @Override
    public LoginStartPacket decode(ByteBuf byteBuf) throws IOException {
        final String playerName = ByteBufUtils.readUTF8(byteBuf, 16);
        return new LoginStartPacket(playerName);
    }
}
