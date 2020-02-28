package rocks.cleanstone.endpoint.minecraft.vanilla.v1_12_2.net.protocol.outbound;


import io.netty.buffer.ByteBuf;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound.OutPluginMessagePacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Codec
public class OutPluginMessageCodec implements OutboundPacketCodec<OutPluginMessagePacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, OutPluginMessagePacket packet) throws IOException {

        ByteBufUtils.writeUTF8(byteBuf, packet.getChannel());

        for (int i = 0; i < packet.getData().length; i++) {
            byteBuf.writeByte(packet.getData()[i]);
        }

        return byteBuf;
    }
}
