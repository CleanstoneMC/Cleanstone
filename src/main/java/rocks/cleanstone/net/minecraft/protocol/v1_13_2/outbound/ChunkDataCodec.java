package rocks.cleanstone.net.minecraft.protocol.v1_13_2.outbound;

import io.netty.buffer.ByteBuf;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.block.state.mapping.BlockStateMapping;
import rocks.cleanstone.net.minecraft.chunk.ChunkDataEncoder;
import rocks.cleanstone.net.minecraft.packet.outbound.ChunkDataPacket;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;

@Component
public class ChunkDataCodec implements OutboundPacketCodec<ChunkDataPacket> {

    private final BlockStateMapping<Integer> blockStateMapping;
    private final ChunkDataEncoder chunkDataEncoder;

    public ChunkDataCodec(@Qualifier("protocolBlockStateMapping_v1_13_2") BlockStateMapping<Integer> blockStateMapping,
                          @Qualifier("chunkDataEncoder_v1_12_2") ChunkDataEncoder chunkDataEncoder) {
        this.blockStateMapping = blockStateMapping;
        this.chunkDataEncoder = chunkDataEncoder;
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, ChunkDataPacket packet) {
        chunkDataEncoder.encodeChunk(byteBuf, packet, blockStateMapping, 14);

        return byteBuf;
    }
}
