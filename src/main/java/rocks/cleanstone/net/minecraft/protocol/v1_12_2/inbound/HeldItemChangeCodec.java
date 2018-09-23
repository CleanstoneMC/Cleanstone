package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.inbound.HeldItemChangePacket;
import rocks.cleanstone.net.protocol.PacketCodec;

@Component
public class HeldItemChangeCodec implements PacketCodec<HeldItemChangePacket> {

    @Override
    public HeldItemChangePacket decode(ByteBuf byteBuf) {
        short slot = byteBuf.readShort();

        return new HeldItemChangePacket(slot);
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, HeldItemChangePacket packet) {
        throw new UnsupportedOperationException("HeldItemChange is inbound and cannot be encoded");
    }
}
