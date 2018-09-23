package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.inbound.InTabCompletePacket;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;
import rocks.cleanstone.utils.Vector;

import java.io.IOException;

@Component
public class InTabCompleteCodec implements PacketCodec<InTabCompletePacket> {

    @Override
    public InTabCompletePacket decode(ByteBuf byteBuf) throws IOException {
        final String text = ByteBufUtils.readUTF8(byteBuf);

        final boolean assumeCommand = byteBuf.readBoolean();
        final boolean hasPosition = byteBuf.readBoolean();
        final Vector lookedAtBlock;

        if (hasPosition) {
            lookedAtBlock = ByteBufUtils.readVector(byteBuf);
        } else {
            lookedAtBlock = null;
        }


        return new InTabCompletePacket(text, assumeCommand, hasPosition, lookedAtBlock);
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, InTabCompletePacket packet) {
        throw new UnsupportedOperationException("TabCompletion is inbound and cannot be encoded");
    }
}
