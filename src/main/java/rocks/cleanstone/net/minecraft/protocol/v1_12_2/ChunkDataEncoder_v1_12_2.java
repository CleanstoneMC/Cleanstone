package rocks.cleanstone.net.minecraft.protocol.v1_12_2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.ReferenceCountUtil;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.block.state.mapping.BlockStateMapping;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.net.minecraft.chunk.ChunkDataEncoder;
import rocks.cleanstone.net.minecraft.chunk.DirectPalette;
import rocks.cleanstone.net.minecraft.chunk.EntrySizeBasedStorage;
import rocks.cleanstone.net.utils.ByteBufUtils;
import rocks.cleanstone.storage.chunk.BlockDataStorage;

import java.io.IOException;

/**
 * The ChunkDataEncoder_v1_12_2 represents a ChunkDataEncoder for the 1.12.2 and compatible Protocols.
 * It needs the {@link BlockDataStorage}, {@link BlockStateMapping} and the bitsPerEntry and returns the Chunk as {@link ByteBuf}
 */
@Component("chunkDataEncoder_v1_12_2")
public class ChunkDataEncoder_v1_12_2 implements ChunkDataEncoder {

    public static final int HEIGHT = Chunk.HEIGHT / 16, WIDTH = Chunk.WIDTH;
    private static final int MINIMUM_BITS_PER_ENTRY_FOR_INDIRECT_PALETTE = 4,
            MAX_BITS_PER_ENTRY_FOR_INDIRECT_PALETTE = 8;

    @Override
    public ByteBuf encodeChunk(BlockDataStorage blockDataStorage, BlockStateMapping<Integer> blockStateMapping, int bitsPerEntry) {
        DirectPalette directPalette = new DirectPalette(blockStateMapping, bitsPerEntry);
        final EntrySizeBasedStorage entrySizeBasedStorage = new EntrySizeBasedStorage(bitsPerEntry, 4096);

        ByteBuf buffer = Unpooled.buffer();
        int primaryBitMask = 0;

        ByteBuf sectionBuffer = Unpooled.buffer();
        for (int sectionY = 0; sectionY < Chunk.HEIGHT / HEIGHT; sectionY++) {
            writeChunkSection(sectionBuffer, bitsPerEntry, entrySizeBasedStorage, blockDataStorage);
            primaryBitMask |= (1 << sectionY);
        }

        ByteBufUtils.writeVarInt(buffer, primaryBitMask);

        ByteBufUtils.writeVarInt(buffer, sectionBuffer.readableBytes());

        buffer.writeBytes(sectionBuffer);
        ReferenceCountUtil.release(sectionBuffer);

        for (int z = 0; z < Chunk.WIDTH; z++) {
            for (int x = 0; x < Chunk.WIDTH; x++) {
                buffer.writeByte(127);  // TODO write biome data
            }
        }

        return buffer;
    }

    private void writeChunkSection(ByteBuf buffer, int bitsPerEntry, EntrySizeBasedStorage entrySizeBasedStorage, BlockDataStorage blockDataStorage) {
        buffer.writeByte(bitsPerEntry); //Write BitsPerEntry
        ByteBufUtils.writeVarInt(buffer, 0); // Write indirectPalette Length

//            for (BlockState state : indirectPalette) {
//                ByteBufUtils.writeVarInt(out, directPalette.getIndex(state));
//            }

        long[] data = entrySizeBasedStorage.getData();
        ByteBufUtils.writeVarInt(buffer, data.length);
        for (long dataItem : data) {
            buffer.writeLong(dataItem);
        }

        writeLights(buffer, blockDataStorage, true); // Write Block Light
        if (blockDataStorage.hasSkylight()) {
            writeLights(buffer, blockDataStorage, false); // Write Sky Light
        }
    }

    private void writeLights(ByteBuf buf, BlockDataStorage blockDataStorage, boolean isBlockLight) {
        for (int y = 0; y < HEIGHT; y++) {
            for (int z = 0; z < WIDTH; z++) {
                for (int x = 0; x < WIDTH; x += 2) {
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
