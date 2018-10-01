package rocks.cleanstone.net.minecraft.protocol.v1_13.outbound;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.chat.message.ChatMessage;
import rocks.cleanstone.game.command.completion.CompletionMatch;
import rocks.cleanstone.net.minecraft.packet.outbound.OutTabCompletePacket;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

@Component
public class OutTabCompleteCodec implements OutboundPacketCodec<OutTabCompletePacket> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ByteBuf encode(ByteBuf byteBuf, OutTabCompletePacket packet) {
        ByteBufUtils.writeVarInt(byteBuf, packet.getTransactionId());
        ByteBufUtils.writeVarInt(byteBuf, packet.getStart());
        ByteBufUtils.writeVarInt(byteBuf, packet.getLength());
        ByteBufUtils.writeVarInt(byteBuf, packet.getMatches().size());

        for (CompletionMatch match : packet.getMatches()) {
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
}
