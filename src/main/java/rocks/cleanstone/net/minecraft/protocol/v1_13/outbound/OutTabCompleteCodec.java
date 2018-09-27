package rocks.cleanstone.net.minecraft.protocol.v1_13.outbound;

import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.chat.message.ChatMessage;
import rocks.cleanstone.game.command.completion.CompletionMatch;
import rocks.cleanstone.net.minecraft.packet.outbound.OutTabCompletePacket;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Component
public class OutTabCompleteCodec implements PacketCodec<OutTabCompletePacket> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public OutTabCompletePacket decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("TabComplete is outbound and cannot be decoded");
    }

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
