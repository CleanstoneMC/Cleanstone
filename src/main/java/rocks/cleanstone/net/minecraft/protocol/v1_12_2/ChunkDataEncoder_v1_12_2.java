package rocks.cleanstone.net.minecraft.protocol.v1_12_2;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.block.state.mapping.BlockStateMapping;
import rocks.cleanstone.net.minecraft.chunk.ChunkDataEncoder;
import rocks.cleanstone.net.minecraft.chunk.DirectPalette;
import rocks.cleanstone.storage.chunk.BlockDataStorage;

import java.io.IOException;

@Component("chunkDataEncoder_v1_12_2")
public class ChunkDataEncoder_v1_12_2 implements ChunkDataEncoder {

    @Override
    public ByteBuf encodeChunk(BlockDataStorage blockDataStorage, BlockStateMapping<Integer> blockStateMapping, int bitsPerEntry) {
        DirectPalette directPalette = new DirectPalette(blockStateMapping, bitsPerEntry);

        return null;
    }

    @Override
    public BlockDataStorage decode(ByteBuf data) throws IOException {
        return null;
    }

    @Override
    public ByteBuf encode(BlockDataStorage value) throws IOException {
        return null;
    }
}
