package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.packet.outbound.PongPacket;
import rocks.cleanstone.net.minecraft.protocol.MinecraftPacketCodec;
import rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.protocol.ProtocolState;

public class PongCodec implements MinecraftPacketCodec {

    @Override
    public Packet decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("Pong is outbound and cannot be decoded");
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) throws IOException {
        PongPacket pongPacket = (PongPacket) packet;

        byteBuf.writeLong(pongPacket.getPayload());
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

    @Override
    public int getProtocolPacketID() {
        return 0x01;
    }

    @Override
    public ProtocolState getProtocolState() {
        return VanillaProtocolState.STATUS;
    }
}
