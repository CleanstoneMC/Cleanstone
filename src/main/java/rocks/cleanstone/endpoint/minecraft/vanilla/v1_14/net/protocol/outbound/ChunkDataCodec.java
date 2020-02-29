package rocks.cleanstone.endpoint.minecraft.vanilla.v1_14.net.protocol.outbound;

import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound.ChunkDataPacket;
import rocks.cleanstone.endpoint.minecraft.vanilla.v1_14.net.protocol.oldchunkdata.OldChunkDataEncoder;
import rocks.cleanstone.game.block.state.mapping.BlockStateMapping;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;

@Codec
public class ChunkDataCodec implements OutboundPacketCodec<ChunkDataPacket> {

    private final BlockStateMapping<Integer> blockStateMapping;
    private final OldChunkDataEncoder chunkDataEncoder;

    public ChunkDataCodec(@Qualifier("oldChunkDataEncoder_v1_14") OldChunkDataEncoder chunkDataEncoder,
                          @Qualifier("protocolBlockStateMapping_v1_14") BlockStateMapping<Integer> blockStateMapping) {
        this.blockStateMapping = blockStateMapping;
        this.chunkDataEncoder = chunkDataEncoder;
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, ChunkDataPacket packet) throws IOException {
        ByteBuf encoded = chunkDataEncoder.encode(packet, blockStateMapping, 14);
        byteBuf.writeBytes(encoded);

        return byteBuf;
    }
}
