package rocks.cleanstone.endpoint.minecraft.vanilla.legacy.v1_12_2.net.protocol.inbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.inbound.InTabCompletePacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.InboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;
import rocks.cleanstone.utils.Vector;

import java.io.IOException;

@Codec
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
