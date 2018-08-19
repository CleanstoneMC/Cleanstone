package rocks.cleanstone.net.minecraft.protocol.v1_13.outbound;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rocks.cleanstone.game.chat.message.ChatMessage;
import rocks.cleanstone.game.command.completion.CompletionMatch;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.outbound.OutTabCompletePacket;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class OutTabCompleteCodec implements PacketCodec {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Packet decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("TabComplete is outbound and cannot be decoded");
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        OutTabCompletePacket tabCompletePacket = (OutTabCompletePacket) packet;

        ByteBufUtils.writeVarInt(byteBuf, tabCompletePacket.getTransactionId());
        ByteBufUtils.writeVarInt(byteBuf, tabCompletePacket.getStart());
        ByteBufUtils.writeVarInt(byteBuf, tabCompletePacket.getLength());
        ByteBufUtils.writeVarInt(byteBuf, tabCompletePacket.getMatches().size());

        for (CompletionMatch match : tabCompletePacket.getMatches()) {
            try {
                ByteBufUtils.writeUTF8(byteBuf, match.getMatch());

                ChatMessage tooltip = match.getTooltip();
                byteBuf.writeBoolean(tooltip != null);
                if (tooltip != null) {
                    ByteBufUtils.writeUTF8(byteBuf, tooltip.toString());
                }
            } catch (IOException e) {
                logger.error("Error while writing TabCompletePacket", e);
            }
        }

        return byteBuf;
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
