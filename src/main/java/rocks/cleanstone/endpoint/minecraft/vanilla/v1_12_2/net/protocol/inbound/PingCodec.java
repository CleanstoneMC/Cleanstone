package rocks.cleanstone.endpoint.minecraft.vanilla.v1_12_2.net.protocol.inbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.inbound.PingPacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.InboundPacketCodec;

@Codec
public class PingCodec implements InboundPacketCodec<PingPacket> {

    @Override
    public PingPacket decode(ByteBuf byteBuf) {
        final long payload = byteBuf.readLong();

        return new PingPacket(payload);
    }
}
