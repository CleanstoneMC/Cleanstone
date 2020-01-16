package rocks.cleanstone.endpoint.minecraft.java.v1_12_2.net.protocol.outbound;

import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;
import rocks.cleanstone.endpoint.minecraft.java.net.packet.outbound.OutTabCompletePacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;
import java.util.List;

@Slf4j
@Codec
public class OutTabCompleteCodec implements OutboundPacketCodec<OutTabCompletePacket> {
    @Override
    public ByteBuf encode(ByteBuf byteBuf, OutTabCompletePacket packet) {

        ByteBufUtils.writeVarInt(byteBuf, packet.getMatches().size());

        List<String> matches = packet.getMatches();

        for (String match : matches) {
            try {
                ByteBufUtils.writeUTF8(byteBuf, match);
            } catch (IOException e) {
                log.error("Error while writing TabCompletePacket", e);
            }
        }

        return byteBuf;
    }
}
