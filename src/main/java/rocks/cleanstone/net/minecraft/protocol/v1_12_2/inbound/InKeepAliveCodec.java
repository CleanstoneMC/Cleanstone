package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.inbound.InKeepAlivePacket;
import rocks.cleanstone.net.protocol.PacketCodec;

@Component
public class InKeepAliveCodec implements PacketCodec<InKeepAlivePacket> {

    @Override
    public InKeepAlivePacket decode(ByteBuf byteBuf) {
        final long keepAliveID = byteBuf.readLong();

        return new InKeepAlivePacket(keepAliveID);
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, InKeepAlivePacket packet) {
        throw new UnsupportedOperationException("KeepAlive is inbound and cannot be encoded");
    }
}
