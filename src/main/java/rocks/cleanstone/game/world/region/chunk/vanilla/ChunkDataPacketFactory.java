package rocks.cleanstone.game.world.region.chunk.vanilla;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rocks.cleanstone.game.world.region.block.Block;
import rocks.cleanstone.game.world.region.chunk.Chunk;
import rocks.cleanstone.io.vanilla.nbt.NamedBinaryTag;
import rocks.cleanstone.net.minecraft.packet.outbound.ChunkDataPacket;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class ChunkDataPacketFactory {

    private static final int SECTION_WIDTH = Chunk.WIDTH, SECTION_HEIGHT = 16,
            FULL_SIZE_BITS_PER_BLOCK = 13;

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
        data.writeByte(FULL_SIZE_BITS_PER_BLOCK); // bits per block
        ByteBufUtils.writeVarInt(data, 0); // empty palette
        ByteBufUtils.writeVarInt(data, (16 * 16 * 16) * FULL_SIZE_BITS_PER_BLOCK / 64); // data length

        boolean empty = true;
        for (int y = 0; y < Chunk.HEIGHT; y++) {
            // is y-coord last layer of chunk section?
            if ((y + 1) % SECTION_HEIGHT == 0) {
                if (!empty) primaryBitMask |= (1 << (y % SECTION_HEIGHT));
                empty = true;
            }
            for (int z = 0; z < Chunk.WIDTH; z++) {
                for (int x = 0; x < Chunk.WIDTH; x++) {
                    Block block = chunk.getBlock(x, y, z);
                    if (block != null) empty = false;
                    // TODO compacted list of 4096 indices pointing to state IDs in the global palette
                }
            }
        }

        // TODO optional biomes byte array
        // TODO NBT block entities
        return new ChunkDataPacket(chunkX, chunkY, isNewChunk,
                primaryBitMask, data, new NamedBinaryTag[]{});
    }
}
