package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.minecraft.packet.inbound.InKeepAlivePacket;
import rocks.cleanstone.net.minecraft.protocol.MinecraftPacketCodec;
import rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.protocol.ProtocolState;

public class InKeepAliveCodec implements MinecraftPacketCodec {

    @Override
    public Packet decode(ByteBuf byteBuf) {
        final long keepAliveID = byteBuf.readLong();

        return new InKeepAlivePacket(keepAliveID);
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        throw new UnsupportedOperationException("KeepAlive is inbound and cannot be encoded");
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
        return 0x0B;
    }

    @Override
    public ProtocolState getProtocolState() {
        return VanillaProtocolState.PLAY;
    }
}
