package rocks.cleanstone.endpoint.minecraft.vanilla.legacy.v1_12_2.net.protocol.inbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.inbound.RequestPacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.InboundPacketCodec;

@Codec
public class RequestCodec implements InboundPacketCodec<RequestPacket> {

    @Override
    public RequestPacket decode(ByteBuf byteBuf) {
        return new RequestPacket();
    }
}
