package rocks.cleanstone.endpoint.minecraft.vanilla.legacy.v1_12_2.net.protocol.inbound;

import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.inbound.InChatMessagePacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.InboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Codec
public class InChatMessageCodec implements InboundPacketCodec<InChatMessagePacket> {

    @Override
    public InChatMessagePacket decode(ByteBuf byteBuf) throws IOException {
        final String message = ByteBufUtils.readUTF8(byteBuf, 256);
        Preconditions.checkArgument(message.length() > 0, "message cannot be empty");
        return new InChatMessagePacket(message);
    }
}
