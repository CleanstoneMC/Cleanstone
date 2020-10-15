package rocks.cleanstone.endpoint.minecraft.vanilla.legacy.v1_12_2.net.protocol.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound.WindowItemsPacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;

@Codec
public class WindowItemsCodec implements OutboundPacketCodec<WindowItemsPacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, WindowItemsPacket packet) {

        byteBuf.writeByte(packet.getWindowID());
        byteBuf.writeShort(/*windowItemsPacket.getSlots().size()*/0);
        // TODO write slots

        return byteBuf;
    }
}
