package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;


import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.inbound.InPluginMessagePacket;
import rocks.cleanstone.net.protocol.InboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Component
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
