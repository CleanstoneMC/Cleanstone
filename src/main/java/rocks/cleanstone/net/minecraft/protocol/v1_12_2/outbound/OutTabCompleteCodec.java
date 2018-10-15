package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.outbound.OutTabCompletePacket;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;
import java.util.List;

@Component
public class OutTabCompleteCodec implements OutboundPacketCodec<OutTabCompletePacket> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ByteBuf encode(ByteBuf byteBuf, OutTabCompletePacket packet) {

        ByteBufUtils.writeVarInt(byteBuf, packet.getMatches().size());

        final List<String> matches = packet.getMatches();

        for (final String match : matches) {
            try {
                ByteBufUtils.writeUTF8(byteBuf, match);
            } catch (IOException e) {
                logger.error("Error while writing TabCompletePacket", e);
            }
        }

        return byteBuf;
    }
}
