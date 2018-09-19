package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.game.block.state.mapping.BlockStateMapping;
import rocks.cleanstone.net.minecraft.packet.outbound.BlockChangePacket;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

public class BlockChangeCodec implements PacketCodec {

    private final BlockStateMapping<Integer> blockStateMapping;

    public BlockChangeCodec(BlockStateMapping<Integer> blockStateMapping) {
        this.blockStateMapping = blockStateMapping;
    }

    @Override
    public Packet decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("BlockChange is outbound and cannot be decoded");
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) throws IOException {
        BlockChangePacket blockChangePacket = (BlockChangePacket) packet;

        ByteBufUtils.writeVector(byteBuf, blockChangePacket.getPosition().toVector());
        ByteBufUtils.writeVarInt(byteBuf, blockStateMapping.getID(blockChangePacket.getBlockState()));

        return byteBuf;
    }
}
