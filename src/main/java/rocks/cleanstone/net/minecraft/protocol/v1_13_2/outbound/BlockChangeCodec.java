package rocks.cleanstone.net.minecraft.protocol.v1_13_2.outbound;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.game.block.state.mapping.BlockStateMapping;
import rocks.cleanstone.net.minecraft.packet.outbound.BlockChangePacket;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

@Component
public class BlockChangeCodec implements OutboundPacketCodec<BlockChangePacket> {

    private final BlockStateMapping<Integer> blockStateMapping;

    public BlockChangeCodec(@Qualifier("protocolBlockStateMapping_v1_13_2") BlockStateMapping<Integer> blockStateMapping) {
        this.blockStateMapping = blockStateMapping;
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, BlockChangePacket packet) {

        ByteBufUtils.writeVector(byteBuf, packet.getPosition().toVector());
        ByteBufUtils.writeVarInt(byteBuf, blockStateMapping.getID(packet.getBlockState()));

        return byteBuf;
    }
}
