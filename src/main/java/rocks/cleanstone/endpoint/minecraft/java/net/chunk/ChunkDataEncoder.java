package rocks.cleanstone.endpoint.minecraft.java.net.chunk;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.endpoint.minecraft.java.net.packet.outbound.ChunkDataPacket;
import rocks.cleanstone.game.block.state.mapping.BlockStateMapping;

import java.io.IOException;

public interface ChunkDataEncoder {
    ByteBuf encode(ChunkDataPacket chunkDataPacket, BlockStateMapping<Integer> blockStateMapping, int bitsPerEntry) throws IOException;
}
