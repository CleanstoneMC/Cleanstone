package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.inbound.RequestPacket;
import rocks.cleanstone.net.protocol.PacketCodec;

@Component
public class RequestCodec implements PacketCodec<RequestPacket> {

    @Override
    public RequestPacket decode(ByteBuf byteBuf) {
        return new RequestPacket();
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, RequestPacket packet) {
        throw new UnsupportedOperationException("Request is inbound and cannot be encoded");
    }
}
