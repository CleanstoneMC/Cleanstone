package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.outbound.ChangeGameStatePacket;
import rocks.cleanstone.net.protocol.PacketCodec;

import java.io.IOException;

public class ChangeGameStateCodec implements PacketCodec {

    @Override
    public Packet decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("ChangeGameState is outbound and cannot be decoded");
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) throws IOException {
        ChangeGameStatePacket changeGameStatePacket = (ChangeGameStatePacket) packet;

        byteBuf.writeByte(changeGameStatePacket.getReason().getReasonID());
        byteBuf.writeFloat(changeGameStatePacket.getValue());

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
