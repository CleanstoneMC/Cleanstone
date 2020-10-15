package rocks.cleanstone.endpoint.minecraft.vanilla.modern.v1_14.net.protocol.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound.UpdateViewPositionPacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Codec
public class UpdateViewPositionCodec implements OutboundPacketCodec<UpdateViewPositionPacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, UpdateViewPositionPacket packet) throws IOException {
        ByteBufUtils.writeVarInt(byteBuf, packet.getChunkCoords().getX());
        ByteBufUtils.writeVarInt(byteBuf, packet.getChunkCoords().getZ());
        return byteBuf;
    }
}
