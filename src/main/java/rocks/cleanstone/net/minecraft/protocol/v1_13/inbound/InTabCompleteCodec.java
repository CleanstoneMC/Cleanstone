package rocks.cleanstone.net.minecraft.protocol.v1_13.inbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.inbound.InTabCompletePacket;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Component
public class InTabCompleteCodec implements PacketCodec<InTabCompletePacket> {

    @Override
    public InTabCompletePacket decode(ByteBuf byteBuf) throws IOException {
        final int transactionId = ByteBufUtils.readVarInt(byteBuf);
        final String text = ByteBufUtils.readUTF8(byteBuf, 32500);

        return new InTabCompletePacket(transactionId, text, true, null);
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, InTabCompletePacket packet) {
        throw new UnsupportedOperationException("TabCompletion is inbound and cannot be encoded");
    }
}
