package rocks.cleanstone.net.minecraft.chunk;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.data.OutboundCodec;
import rocks.cleanstone.game.block.state.mapping.BlockStateMapping;
import rocks.cleanstone.net.minecraft.packet.outbound.ChunkDataPacket;

import java.io.IOException;

public interface ChunkDataEncoder {
    ByteBuf encode(ChunkDataPacket chunkDataPacket, BlockStateMapping<Integer> blockStateMapping, int bitsPerEntry) throws IOException;
}
