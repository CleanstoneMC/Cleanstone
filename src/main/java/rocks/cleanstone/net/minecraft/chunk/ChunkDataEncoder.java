package rocks.cleanstone.net.minecraft.chunk;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.data.InOutCodec;
import rocks.cleanstone.game.block.state.mapping.BlockStateMapping;
import rocks.cleanstone.net.minecraft.packet.outbound.ChunkDataPacket;
import rocks.cleanstone.storage.chunk.BlockDataStorage;

import java.io.IOException;

public interface ChunkDataEncoder extends InOutCodec<BlockDataStorage, ByteBuf> {

    void encodeChunk(ByteBuf byteBuf, ChunkDataPacket blockDataStorage, BlockStateMapping<Integer> blockStateMapping, int bitsPerEntry);

    @Override
    BlockDataStorage decode(ByteBuf data) throws IOException;

    @Override
    ByteBuf encode(BlockDataStorage value) throws IOException;
}
