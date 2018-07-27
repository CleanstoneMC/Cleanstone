package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.outbound.WindowItemsPacket;
import rocks.cleanstone.net.protocol.PacketCodec;

public class WindowItemsCodec implements PacketCodec {

    @Override
    public Packet decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("WindowItems is outbound and cannot be decoded");
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        WindowItemsPacket windowItemsPacket = (WindowItemsPacket) packet;

        byteBuf.writeByte(windowItemsPacket.getWindowID());
        byteBuf.writeShort(/*windowItemsPacket.getSlots().size()*/0);
        // TODO write slots

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
