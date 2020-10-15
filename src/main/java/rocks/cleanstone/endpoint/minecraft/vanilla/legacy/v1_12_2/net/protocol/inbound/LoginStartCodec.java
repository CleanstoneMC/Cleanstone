package rocks.cleanstone.endpoint.minecraft.vanilla.legacy.v1_12_2.net.protocol.inbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.inbound.LoginStartPacket;
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
