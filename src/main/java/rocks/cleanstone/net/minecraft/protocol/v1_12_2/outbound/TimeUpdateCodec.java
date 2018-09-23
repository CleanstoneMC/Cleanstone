package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.outbound.TimeUpdatePacket;
import rocks.cleanstone.net.protocol.PacketCodec;

@Component
public class TimeUpdateCodec implements PacketCodec<TimeUpdatePacket> {

    @Override
    public TimeUpdatePacket decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("TimeUpdate is outbound and cannot be decoded");
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, TimeUpdatePacket packet) {

        byteBuf.writeLong(packet.getWorldAge());
        byteBuf.writeLong(packet.getTimeOfDay());

        return byteBuf;
    }
}
