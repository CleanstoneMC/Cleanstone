package rocks.cleanstone.net.minecraft.protocol.v1_12_2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.ReferenceCountUtil;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.block.state.mapping.BlockStateMapping;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.net.minecraft.chunk.ChunkDataEncoder;
import rocks.cleanstone.net.minecraft.chunk.DirectPalette;
import rocks.cleanstone.net.minecraft.chunk.PaletteBlockStateStorage;
import rocks.cleanstone.net.utils.ByteBufUtils;
import rocks.cleanstone.storage.chunk.BlockDataStorage;

import java.io.IOException;

/**
 * The ChunkDataEncoder_v1_12_2 represents a ChunkDataEncoder for the 1.12.2 and compatible Protocols.
 * It needs the {@link BlockDataStorage}, {@link BlockStateMapping} and the bitsPerEntry and returns the Chunk as {@link ByteBuf}
 */
@Component("chunkDataEncoder_v1_12_2")
public class ChunkDataEncoder_v1_12_2 implements ChunkDataEncoder {



    @Override
    public ByteBuf encodeChunk(BlockDataStorage blockDataStorage, BlockStateMapping<Integer> blockStateMapping, int bitsPerEntry, boolean isFullChunk) {
        ByteBuf buffer = Unpooled.buffer();
        int primaryBitMask = 0;

        ByteBuf sectionBuffer = Unpooled.buffer();
        for (int sectionY = 0; sectionY < PaletteBlockStateStorage.SECTION_HEIGHT; sectionY++) {
            DirectPalette directPalette = new DirectPalette(blockStateMapping, bitsPerEntry);

            PaletteBlockStateStorage paletteBlockStateStorage = new PaletteBlockStateStorage(blockDataStorage, sectionY, directPalette, false);

            paletteBlockStateStorage.write(buffer);

            writeLights(buffer, blockDataStorage, true); // Write Block Light
            if (blockDataStorage.hasSkylight()) {
                writeLights(buffer, blockDataStorage, false); // Write Sky Light
            }

            primaryBitMask |= (1 << sectionY);
        }

        ByteBufUtils.writeVarInt(buffer, primaryBitMask);

        ByteBufUtils.writeVarInt(buffer, sectionBuffer.readableBytes());
        buffer.writeBytes(sectionBuffer);
        ReferenceCountUtil.release(sectionBuffer);

        if (isFullChunk) {
            for (int z = 0; z < Chunk.WIDTH; z++) {
                for (int x = 0; x < Chunk.WIDTH; x++) {
                    buffer.writeByte(127);  // TODO write biome data
                }
            }
        }

        return buffer;
    }

    private void writeLights(ByteBuf buf, BlockDataStorage blockDataStorage, boolean isBlockLight) {
        for (int y = 0; y < PaletteBlockStateStorage.SECTION_HEIGHT; y++) {
            for (int z = 0; z < Chunk.WIDTH; z++) {
                for (int x = 0; x < Chunk.WIDTH; x += 2) {
                    byte light = isBlockLight ? blockDataStorage.getBlockLight(x, y, z) : blockDataStorage.getSkyLight(x, y, z);
                    byte upperLight = isBlockLight ? blockDataStorage.getBlockLight(x + 1, y, z) : blockDataStorage.getSkyLight(x + 1, y, z);
                    byte value = (byte) ((light & 0xff) | (upperLight << 4));
                    buf.writeByte(value);
                }
            }
        }
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
