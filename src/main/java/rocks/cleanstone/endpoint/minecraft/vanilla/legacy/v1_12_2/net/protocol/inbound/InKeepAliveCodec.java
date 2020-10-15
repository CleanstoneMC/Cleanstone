package rocks.cleanstone.endpoint.minecraft.vanilla.legacy.v1_12_2.net.protocol.inbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.inbound.InKeepAlivePacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.InboundPacketCodec;

@Codec
public class InKeepAliveCodec implements InboundPacketCodec<InKeepAlivePacket> {

    @Override
    public InKeepAlivePacket decode(ByteBuf byteBuf) {
        final long keepAliveID = byteBuf.readLong();

        return new InKeepAlivePacket(keepAliveID);
    }
}
