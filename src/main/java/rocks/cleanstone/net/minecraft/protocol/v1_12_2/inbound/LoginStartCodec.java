package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.inbound.LoginStartPacket;
import rocks.cleanstone.net.protocol.InboundPacketCodec;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Component
public class LoginStartCodec implements InboundPacketCodec<LoginStartPacket> {
    @Override
    public LoginStartPacket decode(ByteBuf byteBuf) throws IOException {
        final String playerName = ByteBufUtils.readUTF8(byteBuf, 16);
        return new LoginStartPacket(playerName);
    }
}
