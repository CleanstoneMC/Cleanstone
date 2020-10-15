package rocks.cleanstone.endpoint.minecraft.vanilla.legacy.v1_12_2.net.protocol.inbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.inbound.PlayerPacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.InboundPacketCodec;

import java.io.IOException;

@Codec
public class PlayerCodec implements InboundPacketCodec<PlayerPacket> {
    @Override
    public PlayerPacket decode(ByteBuf byteBuf) throws IOException {
        return new PlayerPacket(byteBuf.readBoolean());
    }
}
