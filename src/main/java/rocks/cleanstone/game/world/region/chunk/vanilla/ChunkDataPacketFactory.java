package rocks.cleanstone.game.world.region.chunk.vanilla;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.BlockState;
import rocks.cleanstone.game.world.region.chunk.Chunk;
import rocks.cleanstone.data.vanilla.nbt.NamedBinaryTag;
import rocks.cleanstone.net.packet.outbound.ChunkDataPacket;

public class ChunkDataPacketFactory {

    private static final int SECTION_WIDTH = Chunk.WIDTH, SECTION_HEIGHT = 16;

    private static final Logger logger = LoggerFactory.getLogger(ChunkDataPacketFactory.class);

    private ChunkDataPacketFactory() {
    }

    /**
     * @param isNewChunk States whether it's the first time that the client receives a chunk at these chunk
     *                   coordinates (UnloadChunkPacket resets this)
     * @return A new ChunkDataPacket that will be closed and therefore unusable after being sent and encoded
     */
    public static ChunkDataPacket create(Chunk chunk, boolean isNewChunk) {
        int primaryBitMask = 0;
        ByteBuf data = Unpooled.buffer();

        for (int sectionY = 0; sectionY < Chunk.HEIGHT / SECTION_HEIGHT; sectionY++) {
            if (writeChunkSection(data, chunk, sectionY)) {
                primaryBitMask |= (1 << sectionY);
            }
        }

        for (int z = 0; z < SECTION_WIDTH; z++) {
            for (int x = 0; x < SECTION_WIDTH; x++) {
                data.writeByte(127);  // "void" biome
            }
        }
        // TODO optional NBT block entities
        return new ChunkDataPacket(chunk.getX(), chunk.getY(), isNewChunk, primaryBitMask, data, new NamedBinaryTag[]{});
    }

    /**
     * @return TRUE if the ChunkSection wasn't empty and data was written, FALSE if nothing was written and
     * the ChunkSection is empty
     */
    private static boolean writeChunkSection(ByteBuf data, Chunk chunk, int sectionY) {
        // block data
        AtomicBoolean isEmptyFlag = new AtomicBoolean();
        PaletteBlockStorage storage = getBlockData(sectionY, chunk, isEmptyFlag);
        if (isEmptyFlag.get()) return false;
        storage.write(data);
        // block light
        writeLight(data, chunk, true);
        // sky light
        if (chunk.hasSkylight())
            writeLight(data, chunk, false);
        return true;
    }

    private static PaletteBlockStorage getBlockData(int sectionY, Chunk chunk, AtomicBoolean isEmptyFlag) {
        PaletteBlockStorage storage = new PaletteBlockStorage();
        isEmptyFlag.set(true);

        for (int y = sectionY * SECTION_HEIGHT; y < sectionY * SECTION_HEIGHT + SECTION_HEIGHT; y++) {
            for (int z = 0; z < SECTION_WIDTH; z++) {
                for (int x = 0; x < SECTION_WIDTH; x++) {
                    Block block = chunk.getBlock(x, y, z);
                    BlockState blockState = block != null ? block.getState() : null;
                    if (blockState != null) {
                        isEmptyFlag.set(false);
                        storage.set(x, y, z, blockState);
                    }
                }
            }
        }
        return storage;
    }

    private static void writeLight(ByteBuf data, Chunk chunk, boolean isBlockLight) {
        for (int y = 0; y < SECTION_HEIGHT; y++) {
            for (int z = 0; z < SECTION_WIDTH; z++) {
                for (int x = 0; x < SECTION_WIDTH; x += 2) {
                    byte light = isBlockLight ? chunk.getBlockLight(x, y, z) : chunk.getSkyLight(x, y, z);
                    byte upperLight = isBlockLight ? chunk.getBlockLight(x + 1, y, z) : chunk.getSkyLight(x + 1, y, z);
                    byte value = (byte) (light | (upperLight << 4));
                    data.writeByte(value);
                }
            }
        }
    }
}
