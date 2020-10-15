package rocks.cleanstone.endpoint.minecraft.vanilla.modern.v1_14.net.protocol.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound.JoinGamePacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Codec
public class JoinGameCodec implements OutboundPacketCodec<JoinGamePacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, JoinGamePacket packet) throws IOException {

        byteBuf.writeInt(packet.getEntityID());
        byteBuf.writeByte(packet.getGamemode().getTypeId());
        byteBuf.writeInt(packet.getDimension().getDimensionID());
        byteBuf.writeByte(packet.getMaxPlayers());
        ByteBufUtils.writeUTF8(byteBuf, packet.getLevelType().getLevelType());
        ByteBufUtils.writeVarInt(byteBuf, 16);//TODO: Get correct Render Distance
        byteBuf.writeBoolean(packet.isReduceDebugInfo());

        return byteBuf;
    }
}
