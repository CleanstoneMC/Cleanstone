package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.outbound.PongPacket;
import rocks.cleanstone.net.protocol.PacketCodec;

@Component
public class PongCodec implements PacketCodec<PongPacket> {

    @Override
    public PongPacket decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("Pong is outbound and cannot be decoded");
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, PongPacket packet) {

        byteBuf.writeLong(packet.getPayload());
        return byteBuf;
    }
}
