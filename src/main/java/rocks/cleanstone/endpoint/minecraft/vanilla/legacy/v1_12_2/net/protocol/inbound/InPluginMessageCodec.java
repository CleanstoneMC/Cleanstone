package rocks.cleanstone.endpoint.minecraft.vanilla.legacy.v1_12_2.net.protocol.inbound;


import io.netty.buffer.ByteBuf;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.inbound.InPluginMessagePacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.InboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Codec
public class InPluginMessageCodec implements InboundPacketCodec<InPluginMessagePacket> {

    @Override
    public InPluginMessagePacket decode(ByteBuf byteBuf) throws IOException {
        final String channel = ByteBufUtils.readUTF8(byteBuf);

        byte[] bytes = new byte[byteBuf.readableBytes()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = byteBuf.readByte();
        }

        return new InPluginMessagePacket(channel, bytes);
    }
}
