package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.inbound.PlayerPacket;
import rocks.cleanstone.net.protocol.InboundPacketCodec;

@Component
public class PlayerCodec implements InboundPacketCodec<PlayerPacket> {
    @Override
    public PlayerPacket decode(ByteBuf byteBuf) throws IOException {
        return new PlayerPacket(byteBuf.readBoolean());
    }
}
