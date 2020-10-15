package rocks.cleanstone.endpoint.minecraft.vanilla.legacy.v1_12_2.net.protocol.outbound;

import io.netty.buffer.ByteBuf;
import org.springframework.beans.factory.annotation.Qualifier;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound.BlockChangePacket;
import rocks.cleanstone.game.block.state.mapping.BlockStateMapping;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

@Codec
public class BlockChangeCodec implements OutboundPacketCodec<BlockChangePacket> {

    private final BlockStateMapping<Integer> blockStateMapping;

    public BlockChangeCodec(@Qualifier("protocolBlockStateMapping_v1_12_2") BlockStateMapping<Integer> blockStateMapping) {
        this.blockStateMapping = blockStateMapping;
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, BlockChangePacket packet) {

        ByteBufUtils.writeVector(byteBuf, packet.getPosition().toVector(), true);
        ByteBufUtils.writeVarInt(byteBuf, blockStateMapping.getID(packet.getBlockState()));

        return byteBuf;
    }
}
