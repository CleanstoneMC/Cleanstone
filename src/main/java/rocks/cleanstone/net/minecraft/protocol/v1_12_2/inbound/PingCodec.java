package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.minecraft.packet.inbound.PingPacket;
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
