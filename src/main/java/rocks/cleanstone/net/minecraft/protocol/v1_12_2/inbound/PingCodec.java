package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.inbound.PingPacket;
import rocks.cleanstone.net.protocol.PacketCodec;

@Component
public class PingCodec implements PacketCodec<PingPacket> {

    @Override
    public PingPacket decode(ByteBuf byteBuf) {
        final long payload = byteBuf.readLong();

        return new PingPacket(payload);
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, PingPacket packet) {
        throw new UnsupportedOperationException("Ping is inbound and cannot be encoded");
    }
}
