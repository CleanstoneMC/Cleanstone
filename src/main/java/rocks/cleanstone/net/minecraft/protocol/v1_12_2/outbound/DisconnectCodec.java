package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.minecraft.packet.outbound.DisconnectPacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Codec
public class DisconnectCodec implements OutboundPacketCodec<DisconnectPacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, DisconnectPacket packet) throws IOException {

        ByteBufUtils.writeUTF8(byteBuf, packet.getReason().toString());
        return byteBuf;
    }
}
