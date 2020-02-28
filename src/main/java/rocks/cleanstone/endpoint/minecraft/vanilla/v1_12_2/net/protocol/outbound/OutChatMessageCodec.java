package rocks.cleanstone.endpoint.minecraft.vanilla.v1_12_2.net.protocol.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound.OutChatMessagePacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Codec
public class OutChatMessageCodec implements OutboundPacketCodec<OutChatMessagePacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, OutChatMessagePacket packet) throws IOException {

        ByteBufUtils.writeUTF8(byteBuf, packet.getChat().toString());
        byteBuf.writeByte(packet.getChatPosition().getPositionID());
        return byteBuf;
    }
}
