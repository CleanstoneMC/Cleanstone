package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.inbound.PingPacket;
import rocks.cleanstone.net.protocol.InboundPacketCodec;

@Component
public class PingCodec implements InboundPacketCodec<PingPacket> {

    @Override
    public PingPacket decode(ByteBuf byteBuf) {
        final long payload = byteBuf.readLong();

        return new PingPacket(payload);
    }
}
