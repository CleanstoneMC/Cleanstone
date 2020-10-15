package rocks.cleanstone.endpoint.minecraft.vanilla.modern.v1_13.net.protocol.inbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.inbound.InTabCompletePacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.InboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Codec
public class InTabCompleteCodec implements InboundPacketCodec<InTabCompletePacket> {

    @Override
    public InTabCompletePacket decode(ByteBuf byteBuf) throws IOException {
        int transactionId = ByteBufUtils.readVarInt(byteBuf);
        String text = ByteBufUtils.readUTF8(byteBuf);

        return new InTabCompletePacket("/" + text, transactionId);
    }
}
