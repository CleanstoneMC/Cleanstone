package rocks.cleanstone.endpoint.minecraft.java.v1_13.net.protocol.outbound;

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

    private final BlockStateMapping<Integer> blockStateMapping;
    private final ChunkDataEncoder chunkDataEncoder;

    public ChunkDataCodec(@Qualifier("chunkDataEncoder_v1_13") ChunkDataEncoder chunkDataEncoder,
                          @Qualifier("protocolBlockStateMapping_v1_13") BlockStateMapping<Integer> blockStateMapping) {
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
