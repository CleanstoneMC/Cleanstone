package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.outbound.WindowItemsPacket;
import rocks.cleanstone.net.protocol.PacketCodec;

@Component
public class WindowItemsCodec implements PacketCodec<WindowItemsPacket> {

    @Override
    public WindowItemsPacket decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("WindowItems is outbound and cannot be decoded");
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, WindowItemsPacket packet) {

        byteBuf.writeByte(packet.getWindowID());
        byteBuf.writeShort(/*windowItemsPacket.getSlots().size()*/0);
        // TODO write slots

        return byteBuf;
    }
}
