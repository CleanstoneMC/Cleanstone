package rocks.cleanstone.net.minecraft.protocol.v1_13.outbound;

import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.outbound.OutTabCompletePacket;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

//TODO: This currently brings the client to disconnect with an error

@Slf4j
@Component
public class OutTabCompleteCodec implements OutboundPacketCodec<OutTabCompletePacket> {
    @Override
    public ByteBuf encode(ByteBuf byteBuf, OutTabCompletePacket packet) throws IOException {
        String input = packet.getInTabCompletePacket().getText();

        // Start & End
        int index;
        int length;
        // If no input or new word (then it's the start)
        if (input.endsWith(" ") || input.length() == 0) {
            index = input.length();
            length = 0;
        } else {
            // Otherwise find the last space (+1 as we include it)
            int lastSpace = input.lastIndexOf(" ") + 1;
            index = lastSpace;
            length = input.length() - lastSpace;
        }
        // Write index + length
        ByteBufUtils.writeVarInt(byteBuf, index);
        ByteBufUtils.writeVarInt(byteBuf, length);

        ByteBufUtils.writeVarInt(byteBuf, packet.getMatches().size());
        for (String match : packet.getMatches()) {
            if (match.startsWith("/") && index == 0) {
                match = match.substring(1);
            }
            ByteBufUtils.writeUTF8(byteBuf, match);
            byteBuf.writeBoolean(false);
        }

        return byteBuf;
    }
}
