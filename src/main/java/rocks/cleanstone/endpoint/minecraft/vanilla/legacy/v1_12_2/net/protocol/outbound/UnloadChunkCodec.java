package rocks.cleanstone.endpoint.minecraft.vanilla.legacy.v1_12_2.net.protocol.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound.UnloadChunkPacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;

@Codec
public class UnloadChunkCodec implements OutboundPacketCodec<UnloadChunkPacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, UnloadChunkPacket packet) {

        byteBuf.writeInt(packet.getChunkX());
        byteBuf.writeInt(packet.getchunkZ());

        return byteBuf;
    }
}
