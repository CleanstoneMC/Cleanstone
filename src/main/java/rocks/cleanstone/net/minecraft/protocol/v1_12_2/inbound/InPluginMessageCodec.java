package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;


import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.inbound.PluginMessagePacket;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Component
public class InPluginMessageCodec implements PacketCodec<PluginMessagePacket> {

    @Override
    public PluginMessagePacket decode(ByteBuf byteBuf) throws IOException {
        final String channel = ByteBufUtils.readUTF8(byteBuf);

        byte[] bytes = new byte[byteBuf.readableBytes()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = byteBuf.readByte();
        }

        return new PluginMessagePacket(channel, bytes);
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, PluginMessagePacket packet) {
        throw new UnsupportedOperationException("PluginMessage is inbound and cannot be encoded");
    }
}
