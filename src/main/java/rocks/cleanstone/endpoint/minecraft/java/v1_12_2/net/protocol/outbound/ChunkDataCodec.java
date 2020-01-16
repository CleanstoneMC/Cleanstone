package rocks.cleanstone.endpoint.minecraft.java.v1_12_2.net.protocol.outbound;

import io.netty.buffer.ByteBuf;
import org.springframework.beans.factory.annotation.Qualifier;
import rocks.cleanstone.endpoint.minecraft.java.net.chunk.ChunkDataEncoder;
import rocks.cleanstone.endpoint.minecraft.java.net.packet.outbound.ChunkDataPacket;
import rocks.cleanstone.game.block.state.mapping.BlockStateMapping;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;

import java.io.IOException;

@Codec
public class ChunkDataCodec implements OutboundPacketCodec<ChunkDataPacket> {

    private final ChunkDataEncoder chunkDataEncoder;
    private BlockStateMapping<Integer> blockStateMapping;

    public ChunkDataCodec(@Qualifier("chunkDataEncoder_v1_12_2") ChunkDataEncoder chunkDataEncoder,
                          @Qualifier("protocolBlockStateMapping_v1_12_2") BlockStateMapping<Integer> blockStateMapping) {
        this.chunkDataEncoder = chunkDataEncoder;
        this.blockStateMapping = blockStateMapping;
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, ChunkDataPacket packet) throws IOException {
        ByteBuf encoded = chunkDataEncoder.encode(packet, blockStateMapping, 13);
        byteBuf.writeBytes(encoded);

        return byteBuf;
    }
}
