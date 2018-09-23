package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.block.state.mapping.BlockStateMapping;
import rocks.cleanstone.net.minecraft.packet.outbound.BlockChangePacket;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

@Component
public class BlockChangeCodec implements PacketCodec<BlockChangePacket> {

    private final BlockStateMapping<Integer> blockStateMapping;

    public BlockChangeCodec(@Qualifier("protocolBlockStateMapping_v1_12_2") BlockStateMapping<Integer> blockStateMapping) {
        this.blockStateMapping = blockStateMapping;
    }

    @Override
    public BlockChangePacket decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("BlockChange is outbound and cannot be decoded");
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, BlockChangePacket packet) {

        ByteBufUtils.writeVector(byteBuf, packet.getPosition().toVector());
        ByteBufUtils.writeVarInt(byteBuf, blockStateMapping.getID(packet.getBlockState()));

        return byteBuf;
    }
}
