package rocks.cleanstone.endpoint.minecraft.java.v1_14.net.protocol;

import com.github.steveice10.opennbt.NBTIO;
import com.github.steveice10.opennbt.tag.builtin.CompoundTag;
import com.github.steveice10.opennbt.tag.builtin.LongArrayTag;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.util.ReferenceCountUtil;
import org.springframework.stereotype.Component;
import rocks.cleanstone.endpoint.minecraft.java.net.chunk.ChunkDataEncoder;
import rocks.cleanstone.endpoint.minecraft.java.net.chunk.DirectPalette;
import rocks.cleanstone.endpoint.minecraft.java.net.chunk.HeightMapUtil;
import rocks.cleanstone.endpoint.minecraft.java.net.chunk.PaletteBlockStateStorage;
import rocks.cleanstone.endpoint.minecraft.java.net.packet.outbound.ChunkDataPacket;
import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.block.state.mapping.BlockStateMapping;
import rocks.cleanstone.game.material.block.vanilla.VanillaBlockType;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.net.utils.ByteBufUtils;
import rocks.cleanstone.storage.chunk.BlockDataStorage;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;

@Component("chunkDataEncoder_v1_14")
public class ChunkDataEncoder_v1_14 implements ChunkDataEncoder {
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

    private void writeHeightMap(ByteBuf data, BlockDataStorage storage) throws IOException {
        int[] motionBlocking = new int[Chunk.WIDTH * Chunk.WIDTH];
        for (int sectionY = 0; sectionY < PaletteBlockStateStorage.SECTION_HEIGHT; sectionY++) {
            for (int x = 0; x < Chunk.WIDTH; x++) {
                for (int y = 0; y < Chunk.HEIGHT; y++) {
                    for (int z = 0; z < Chunk.WIDTH; z++) {
                        BlockState blockState = storage.getBlock(x, y, z).getState();
                        if (blockState.getBlockType() != VanillaBlockType.AIR)
                            motionBlocking[x + z * 16] = y + sectionY * 16 + 2;
                    }
                }
            }
        }
        CompoundTag heightMap = new CompoundTag("");
        heightMap.put(new LongArrayTag("MOTION_BLOCKING", HeightMapUtil.encodeHeightMap(motionBlocking)));

        ByteBufOutputStream byteBufStream = new ByteBufOutputStream(data);
        DataOutputStream dataOutputStream = new DataOutputStream(byteBufStream);
        NBTIO.writeTag((DataOutput) dataOutputStream, heightMap);
        dataOutputStream.close();
    }

    private int countNonAirBlocks(int sectionY, BlockDataStorage blockDataStorage) {
        // TODO find less expensive way to calculate non air blocks
        int blockCount = 0;
        for (int y = 0; y < PaletteBlockStateStorage.SECTION_HEIGHT; y++) {
            for (int z = 0; z < Chunk.WIDTH; z++) {
                for (int x = 0; x < Chunk.WIDTH; x++) {
                    BlockState state = blockDataStorage.getBlockState(x, y + PaletteBlockStateStorage.SECTION_HEIGHT * sectionY, z);
                    if (state.getBlockType() != VanillaBlockType.AIR) {
                        blockCount++;
                    }
                }
            }
        }
        return blockCount;
    }

    @Override
    public ByteBuf encode(ChunkDataPacket chunkDataPacket, BlockStateMapping<Integer> blockStateMapping, int bitsPerEntry) throws IOException {
        ByteBuf buffer = Unpooled.buffer();
        BlockDataStorage blockDataStorage = chunkDataPacket.getBlockDataStorage();

        int primaryBitMask = 0;

        ByteBuf sectionBuffer = Unpooled.buffer();
        for (int sectionY = 0; sectionY < PaletteBlockStateStorage.SECTION_HEIGHT; sectionY++) {
            sectionBuffer.writeShort(countNonAirBlocks(sectionY, blockDataStorage));

            DirectPalette directPalette = new DirectPalette(blockStateMapping, bitsPerEntry);

            PaletteBlockStateStorage paletteBlockStateStorage = new PaletteBlockStateStorage(blockDataStorage, sectionY, directPalette, false);
            paletteBlockStateStorage.write(sectionBuffer);

            writeLights(sectionBuffer, blockDataStorage, true); // Write Block Light
            if (blockDataStorage.hasSkylight()) {
                writeLights(sectionBuffer, blockDataStorage, false); // Write Sky Light
            }

            primaryBitMask |= (1 << sectionY);
        }

        if (chunkDataPacket.isGroundUpContinuous()) {
            for (int z = 0; z < Chunk.WIDTH; z++) {
                for (int x = 0; x < Chunk.WIDTH; x++) {
                    sectionBuffer.writeInt(127);  // TODO write biome data
                }
            }
        }

        buffer.writeInt(chunkDataPacket.getChunkX());
        buffer.writeInt(chunkDataPacket.getChunkZ());
        buffer.writeBoolean(chunkDataPacket.isGroundUpContinuous());

        ByteBufUtils.writeVarInt(buffer, primaryBitMask);
        writeHeightMap(buffer, blockDataStorage);

        ByteBufUtils.writeVarInt(buffer, sectionBuffer.readableBytes());
        buffer.writeBytes(sectionBuffer);
        ReferenceCountUtil.release(sectionBuffer);

        ByteBufUtils.writeVarInt(buffer, 0);
        //TODO encode NBT Tag array of block entities
        //ByteBufUtils.writeVarInt(byteBuf,chunkDataPacket.getBlockEntities().length);
        //byteBuf.writeBytes(chunkDataPacket.getBlockEntities())

        return buffer;
    }
}
