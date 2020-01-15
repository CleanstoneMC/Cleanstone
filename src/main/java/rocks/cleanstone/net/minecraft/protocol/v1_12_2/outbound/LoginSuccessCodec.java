package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.minecraft.packet.outbound.LoginSuccessPacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Codec
public class LoginSuccessCodec implements OutboundPacketCodec<LoginSuccessPacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, LoginSuccessPacket packet) throws IOException {

        ByteBufUtils.writeUTF8(byteBuf, packet.getUUID().toString());
        ByteBufUtils.writeUTF8(byteBuf, packet.getUserName());
        return byteBuf;
    }
}
