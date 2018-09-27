package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.inbound.InKeepAlivePacket;
import rocks.cleanstone.net.protocol.InboundPacketCodec;

@Component
public class InKeepAliveCodec implements InboundPacketCodec<InKeepAlivePacket> {

    @Override
    public InKeepAlivePacket decode(ByteBuf byteBuf) {
        final long keepAliveID = byteBuf.readLong();

        return new InKeepAlivePacket(keepAliveID);
    }
}
