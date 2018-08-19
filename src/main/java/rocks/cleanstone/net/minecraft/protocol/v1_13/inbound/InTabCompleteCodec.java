package rocks.cleanstone.net.minecraft.protocol.v1_13.inbound;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.inbound.InTabCompletePacket;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class InTabCompleteCodec implements PacketCodec {

    @Override
    public Packet decode(ByteBuf byteBuf) throws IOException {
        final int transactionId = ByteBufUtils.readVarInt(byteBuf);
        final String text = ByteBufUtils.readUTF8(byteBuf, 32500);

        return new InTabCompletePacket(transactionId, text, true, null);
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        throw new UnsupportedOperationException("TabCompletion is inbound and cannot be encoded");
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
