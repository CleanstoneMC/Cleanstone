package rocks.cleanstone.game.world.region.chunk.vanilla;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Nullable;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rocks.cleanstone.game.material.block.BlockType;
import rocks.cleanstone.game.world.region.chunk.Chunk;
import rocks.cleanstone.io.vanilla.nbt.NamedBinaryTag;
import rocks.cleanstone.net.minecraft.packet.outbound.ChunkDataPacket;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class ChunkDataPacketFactory {

    private static final int SECTION_WIDTH = Chunk.WIDTH, SECTION_HEIGHT = 16, FULL_SIZE_BITS_PER_BLOCK = 13;

    private ChunkDataPacketFactory() {
    }

    /**
     * @param isNewChunk States whether it's the first time that the client receives a chunk at these chunk
     *                   coordinates (UnloadChunkPacket resets this)
     * @return The ChunkDataPacket that must be closed after being sent
     */
    public static ChunkDataPacket create(int chunkX, int chunkY, Chunk chunk, boolean isNewChunk) {
        int primaryBitMask = 0;
        ByteBuf data = Unpooled.buffer();

        for (int sectionY = 0; sectionY < Chunk.HEIGHT / SECTION_HEIGHT; sectionY++) {
            if (writeChunkSection(data, chunk, sectionY)) {
                primaryBitMask |= (1 << (sectionY % SECTION_HEIGHT));
            }
        }

        // TODO optional biomes byte array
        // TODO optional NBT block entities
        return new ChunkDataPacket(chunkX, chunkY, isNewChunk, primaryBitMask, data, new NamedBinaryTag[]{});
    }

    /**
     * @return TRUE if the ChunkSection wasn't empty and data was written, FALSE if nothing was written and
     * the ChunkSection is empty
     */
    private static boolean writeChunkSection(ByteBuf data, Chunk chunk, int sectionY) {
        // block data
        AtomicBoolean isEmptyFlag = new AtomicBoolean();
        long[] blockData = getBlockData(sectionY, chunk, isEmptyFlag);
        if (isEmptyFlag.get()) return false;

        data.writeByte(FULL_SIZE_BITS_PER_BLOCK);
        ByteBufUtils.writeVarInt(data, 0); // empty palette
        ByteBufUtils.writeVarInt(data, blockData.length);
        for (long blockDataItem : blockData) {
            data.writeLong(blockDataItem);
        }
        // block light
        writeLight(data, chunk, true);
        // sky light
        if (chunk.hasSkylight())
            writeLight(data, chunk, false);
        return true;
    }

    private static long[] getBlockData(int sectionY, Chunk chunk, AtomicBoolean isEmptyFlag) {
        byte bitsPerBlock = FULL_SIZE_BITS_PER_BLOCK;
        int dataLength = (16 * 16 * 16) * FULL_SIZE_BITS_PER_BLOCK / 64;
        long[] blockData = new long[dataLength];
        int individualValueMask = (1 << bitsPerBlock) - 1; // bitmask that contains bitsPerBlock set bits
        isEmptyFlag.set(true);
        // block data
        for (int y = sectionY * SECTION_HEIGHT; y < sectionY * SECTION_HEIGHT + SECTION_HEIGHT; y++) {
            for (int z = 0; z < Chunk.WIDTH; z++) {
                for (int x = 0; x < Chunk.WIDTH; x++) {
                    BlockType block = chunk.getBlock(x, y, z).getType();
                    if (block != null) isEmptyFlag.set(false);
                    writeBlock(blockData, block, x, y, z, bitsPerBlock, individualValueMask);
                }
            }
        }
        return blockData;
    }

    private static void writeBlock(long[] blockData, @Nullable BlockType block, int x, int y, int z,
                                   byte bitsPerBlock, int individualValueMask) {
        int blockNumber = (((y * SECTION_HEIGHT) + z) * SECTION_WIDTH) + x;
        int startLong = (blockNumber * bitsPerBlock) / 64;
        int startOffset = (blockNumber * bitsPerBlock) % 64;
        int endLong = ((blockNumber + 1) * bitsPerBlock - 1) / 64;
        int paletteID = getGlobalPaletteID(block);

        paletteID &= individualValueMask;
        blockData[startLong] |= (paletteID << startOffset);
        if (startLong != endLong) {
            blockData[endLong] = (paletteID >> (64 - startOffset));
        }
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

    private static int getGlobalPaletteID(BlockType block) {
        // TODO BlockState metadata
        int blockID = block != null ? block.getMaterial().getID() : 0;
        return blockID >> 4 /* | metadata */;
    }
}
