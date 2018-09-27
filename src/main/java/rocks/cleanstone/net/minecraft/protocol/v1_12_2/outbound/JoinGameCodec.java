package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.outbound.JoinGamePacket;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Component
public class JoinGameCodec implements OutboundPacketCodec<JoinGamePacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, JoinGamePacket packet) throws IOException {

        byteBuf.writeInt(packet.getEntityID());
        byteBuf.writeByte(packet.getGamemode().getTypeId());
        byteBuf.writeInt(packet.getDimension().getDimensionID());
        byteBuf.writeByte(packet.getDifficulty().getDifficultyID());
        byteBuf.writeByte(packet.getMaxPlayers());
        ByteBufUtils.writeUTF8(byteBuf, packet.getLevelType().getLevelType());
        byteBuf.writeBoolean(packet.isReduceDebugInfo());

        return byteBuf;
    }
}
