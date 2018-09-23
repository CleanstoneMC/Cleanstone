package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.inbound.InChatMessagePacket;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Component
public class InChatMessageCodec implements PacketCodec<InChatMessagePacket> {

    @Override
    public InChatMessagePacket decode(ByteBuf byteBuf) throws IOException {
        final String message = ByteBufUtils.readUTF8(byteBuf, 256);
        Preconditions.checkArgument(message.length() > 0, "message cannot be empty");
        return new InChatMessagePacket(message);
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, InChatMessagePacket packet) {
        throw new UnsupportedOperationException("ChatMessage is inbound and cannot be encoded");
    }
}
