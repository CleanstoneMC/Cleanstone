package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.outbound.JoinGamePacket;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

public class JoinGameCodec implements PacketCodec {

    @Override
    public Packet decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("JoinGame is outbound and cannot be decoded");
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) throws IOException {
        JoinGamePacket joinGamePacket = (JoinGamePacket) packet;

        byteBuf.writeInt(joinGamePacket.getEntityID());
        byteBuf.writeByte(joinGamePacket.getGamemode().getTypeId());
        byteBuf.writeInt(joinGamePacket.getDimension().getDimensionID());
        byteBuf.writeByte(joinGamePacket.getDifficulty().getDifficultyID());
        byteBuf.writeByte(joinGamePacket.getMaxPlayers());
        ByteBufUtils.writeUTF8(byteBuf, joinGamePacket.getLevelType().getLevelType());
        byteBuf.writeBoolean(joinGamePacket.isReduceDebugInfo());

        return byteBuf;
    }

    @Override
    public ByteBuf upgradeByteBuf(ByteBuf previousLayerByteBuf) {
        return previousLayerByteBuf;
    }

    @Override
    public ByteBuf downgradeByteBuf(ByteBuf nextLayerByteBuf) {
        return nextLayerByteBuf;
    }
}
