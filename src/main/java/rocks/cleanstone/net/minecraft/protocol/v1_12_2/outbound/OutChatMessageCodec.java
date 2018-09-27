package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.outbound.OutChatMessagePacket;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Component
public class OutChatMessageCodec implements OutboundPacketCodec<OutChatMessagePacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, OutChatMessagePacket packet) throws IOException {

        ByteBufUtils.writeUTF8(byteBuf, packet.getChat().toString());
        byteBuf.writeByte(packet.getChatPosition().getPositionID());
        return byteBuf;
    }
}
