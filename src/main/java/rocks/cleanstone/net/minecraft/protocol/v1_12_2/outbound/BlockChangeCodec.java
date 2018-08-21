package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.outbound.BlockChangePacket;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class BlockChangeCodec implements PacketCodec {

    @Override
    public Packet decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("BlockChange is outbound and cannot be decoded");
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) throws IOException {
        BlockChangePacket blockChangePacket = (BlockChangePacket) packet;

        ByteBufUtils.writeVector(byteBuf, blockChangePacket.getPosition().toVector());
        ByteBufUtils.writeVarInt(byteBuf, blockChangePacket.getBlockData());

        return byteBuf;
    }
}
