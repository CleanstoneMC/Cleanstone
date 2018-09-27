package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.inbound.RequestPacket;
import rocks.cleanstone.net.protocol.InboundPacketCodec;

@Component
public class RequestCodec implements InboundPacketCodec<RequestPacket> {

    @Override
    public RequestPacket decode(ByteBuf byteBuf) {
        return new RequestPacket();
    }
}
