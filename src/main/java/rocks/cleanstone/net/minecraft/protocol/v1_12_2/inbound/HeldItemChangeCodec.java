package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.inbound.HeldItemChangePacket;
import rocks.cleanstone.net.protocol.InboundPacketCodec;

@Component
public class HeldItemChangeCodec implements InboundPacketCodec<HeldItemChangePacket> {

    @Override
    public HeldItemChangePacket decode(ByteBuf byteBuf) {
        short slot = byteBuf.readShort();

        return new HeldItemChangePacket(slot);
    }
}
