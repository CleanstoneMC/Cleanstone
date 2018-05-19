package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.minecraft.packet.outbound.OutTabCompletePacket;
import rocks.cleanstone.net.minecraft.protocol.MinecraftPacketCodec;
import rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.protocol.ProtocolState;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class OutTabCompleteCodec implements MinecraftPacketCodec {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Packet decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("TabComplete is outbound and cannot be decoded");
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        OutTabCompletePacket tabCompletePacket = (OutTabCompletePacket) packet;

        ByteBufUtils.writeVarInt(byteBuf, tabCompletePacket.getMatches().size());

        List<String> matches = tabCompletePacket.getMatches();

        for (String match : matches) {
            try {
                ByteBufUtils.writeUTF8(byteBuf, match);
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

    @Override
    public int getProtocolPacketID() {
        return 0x0E;
    }

    @Override
    public ProtocolState getProtocolState() {
        return VanillaProtocolState.PLAY;
    }
}
