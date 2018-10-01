package rocks.cleanstone.net.minecraft.protocol.v1_13.inbound;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.inbound.InTabCompletePacket;
import rocks.cleanstone.net.protocol.InboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

@Component
public class InTabCompleteCodec implements InboundPacketCodec<InTabCompletePacket> {

    @Override
    public InTabCompletePacket decode(ByteBuf byteBuf) throws IOException {
        final int transactionId = ByteBufUtils.readVarInt(byteBuf);
        final String text = ByteBufUtils.readUTF8(byteBuf, 32500);

        return new InTabCompletePacket(transactionId, text, true, null);
    }
}
