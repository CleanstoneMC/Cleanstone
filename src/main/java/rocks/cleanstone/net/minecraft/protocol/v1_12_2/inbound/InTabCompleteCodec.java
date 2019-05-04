package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import org.springframework.stereotype.Component;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.minecraft.packet.inbound.InTabCompletePacket;
import rocks.cleanstone.net.protocol.InboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;
import rocks.cleanstone.utils.Vector;

@Component
public class InTabCompleteCodec implements InboundPacketCodec<InTabCompletePacket> {

    @Override
    public InTabCompletePacket decode(ByteBuf byteBuf) throws IOException {
        final String text = ByteBufUtils.readUTF8(byteBuf);

        final boolean assumeCommand = byteBuf.readBoolean();
        final boolean hasPosition = byteBuf.readBoolean();
        final Vector lookedAtBlock;

        if (hasPosition) {
            lookedAtBlock = ByteBufUtils.readVector(byteBuf, true);
        } else {
            lookedAtBlock = null;
        }


        return new InTabCompletePacket(text, assumeCommand, hasPosition, lookedAtBlock);
    }
}
