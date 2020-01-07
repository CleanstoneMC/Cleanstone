package rocks.cleanstone.net.minecraft.protocol.v1_13.inbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.inbound.InTabCompletePacket;
import rocks.cleanstone.net.protocol.InboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Component
public class InTabCompleteCodec implements InboundPacketCodec<InTabCompletePacket> {

    @Override
    public InTabCompletePacket decode(ByteBuf byteBuf) throws IOException {
        int transactionId = ByteBufUtils.readVarInt(byteBuf);
        String text = ByteBufUtils.readUTF8(byteBuf);

        return new InTabCompletePacket("/" + text, transactionId);
    }
}
