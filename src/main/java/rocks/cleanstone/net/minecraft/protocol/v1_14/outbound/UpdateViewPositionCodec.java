package rocks.cleanstone.net.minecraft.protocol.v1_14.outbound;

import org.springframework.stereotype.Component;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.minecraft.packet.outbound.UpdateViewPositionPacket;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

@Component
public class UpdateViewPositionCodec implements OutboundPacketCodec<UpdateViewPositionPacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, UpdateViewPositionPacket packet) throws IOException {
        ByteBufUtils.writeVarInt(byteBuf, packet.getChunkCoords().getX());
        ByteBufUtils.writeVarInt(byteBuf, packet.getChunkCoords().getZ());
        return byteBuf;
    }
}
