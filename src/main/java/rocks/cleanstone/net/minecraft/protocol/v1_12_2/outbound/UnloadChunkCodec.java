package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.minecraft.packet.outbound.UnloadChunkPacket;
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
