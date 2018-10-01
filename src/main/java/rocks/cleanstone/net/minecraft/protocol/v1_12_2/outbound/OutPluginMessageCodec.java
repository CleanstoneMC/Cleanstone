package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;


import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.outbound.PluginMessagePacket;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Component
public class OutPluginMessageCodec implements OutboundPacketCodec<PluginMessagePacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, PluginMessagePacket packet) throws IOException {

        ByteBufUtils.writeUTF8(byteBuf, packet.getChannel());

        for (int i = 0; i < packet.getData().length; i++) {
            byteBuf.writeByte(packet.getData()[i]);
        }

        return byteBuf;
    }
}
