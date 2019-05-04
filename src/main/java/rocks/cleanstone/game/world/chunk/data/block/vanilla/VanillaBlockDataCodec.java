package rocks.cleanstone.game.world.chunk.data.block.vanilla;

import com.github.steveice10.opennbt.NBTIO;
import com.github.steveice10.opennbt.tag.builtin.CompoundTag;
import com.github.steveice10.opennbt.tag.builtin.LongArrayTag;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import rocks.cleanstone.data.InOutCodec;
import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.material.block.vanilla.VanillaBlockType;
import rocks.cleanstone.game.world.chunk.BlockDataTable;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.section.BlockDataSection;
import rocks.cleanstone.net.utils.ByteBufUtils;

@Slf4j
public class VanillaBlockDataCodec implements InOutCodec<VanillaBlockDataStorage, ByteBuf> {

    private final VanillaBlockDataStorageFactory vanillaBlockDataStorageFactory;
    private final DirectPalette directPalette;
    private final boolean omitDirectPaletteLength, writeHeightMap, omitLighting;

    public VanillaBlockDataCodec(VanillaBlockDataStorageFactory vanillaBlockDataStorageFactory,
                                 DirectPalette directPalette, boolean omitDirectPaletteLength) {
        this.vanillaBlockDataStorageFactory = vanillaBlockDataStorageFactory;
        this.directPalette = directPalette;
        this.omitDirectPaletteLength = omitDirectPaletteLength;
        this.writeHeightMap = true;
        this.omitLighting = true;
    }

    @Override
    public VanillaBlockDataStorage decode(ByteBuf data) throws IOException {
        BlockDataSection[] sections = new BlockDataSection[Chunk.HEIGHT / BlockDataSection.HEIGHT];
        int primaryBitMask = ByteBufUtils.readVarInt(data);
        int dataSize = ByteBufUtils.readVarInt(data);
        for (int sectionY = 0; sectionY < Chunk.HEIGHT / BlockDataSection.HEIGHT; sectionY++) {
            if ((primaryBitMask & (1 << sectionY)) != 0) {
                BlockDataSection section = new BlockDataSection(data, true, directPalette,
                        omitDirectPaletteLength);
                sections[sectionY] = section;
            }
        }
        return vanillaBlockDataStorageFactory.get(sections, true, directPalette, omitDirectPaletteLength);
    }

    @Override
    public ByteBuf encode(VanillaBlockDataStorage storage) {
        ByteBuf data = Unpooled.buffer();
        int primaryBitMask = 0;

        ByteBuf dataBuf = Unpooled.buffer();
        for (int sectionY = 0; sectionY < Chunk.HEIGHT / BlockDataSection.HEIGHT; sectionY++) {
            BlockDataSection section = storage.getSection(sectionY);
            if (section != null) {
                section.write(dataBuf);
                primaryBitMask |= (1 << sectionY);
            }
        }
        for (int z = 0; z < Chunk.WIDTH; z++) {
            for (int x = 0; x < Chunk.WIDTH; x++) {
                if (!omitDirectPaletteLength)
                    dataBuf.writeByte(127);  // TODO write biome data
                else
                    dataBuf.writeInt(127);
            }
        }

        ByteBufUtils.writeVarInt(data, primaryBitMask);

        if (writeHeightMap) writeHeightMap(data, storage);

        ByteBufUtils.writeVarInt(data, dataBuf.readableBytes());
        data.writeBytes(dataBuf);
        ReferenceCountUtil.release(dataBuf);
        return data;
    }

    private void writeHeightMap(ByteBuf data, VanillaBlockDataStorage storage) {
        BlockDataTable table = storage.constructTable();
        int[] motionBlocking = new int[16 * 16];
        for (int sectionY = 0; sectionY < 16; sectionY++) {
            for (int x = 0; x < 16; x++) {
                for (int y = 0; y < 16; y++) {
                    for (int z = 0; z < 16; z++) {
                        final BlockState blockState =
                                table.getBlock(x, y + sectionY * BlockDataSection.HEIGHT, z).getState();
                        if (blockState.getBlockType() != VanillaBlockType.AIR)
                            motionBlocking[x + z * 16] = y + sectionY * 16 + 2; // Should be +1 (top of the block) but +2 works :tm:
                    }
                }
            }
        }
        CompoundTag heightMap = new CompoundTag("");
        heightMap.put(new LongArrayTag("MOTION_BLOCKING", encodeHeightMap(motionBlocking)));
        //heightMap.put(new LongArrayTag("WORLD_SURFACE", encodeHeightMap(motionBlocking)));

        try {
            ByteBufOutputStream byteBufStream = new ByteBufOutputStream(data);
            DataOutputStream dataOutputStream = new DataOutputStream(byteBufStream);
            NBTIO.writeTag((DataOutput) dataOutputStream, heightMap);
            dataOutputStream.close();
        } catch (IOException e) {
            log.error("Error occurred while serializing heightMap NBT data", e);
        }
    }

    private long[] encodeHeightMap(int[] heightMap) {
        final int bitsPerBlock = 9;
        long maxEntryValue = (1L << bitsPerBlock) - 1;

        int length = (int) Math.ceil(heightMap.length * bitsPerBlock / 64.0);
        long[] data = new long[length];

        for (int index = 0; index < heightMap.length; index++) {
            int value = heightMap[index];
            int bitIndex = index * 9;
            int startIndex = bitIndex / 64;
            int endIndex = ((index + 1) * bitsPerBlock - 1) / 64;
            int startBitSubIndex = bitIndex % 64;
            data[startIndex] = data[startIndex] & ~(maxEntryValue << startBitSubIndex) | ((long) value & maxEntryValue) << startBitSubIndex;
            if (startIndex != endIndex) {
                int endBitSubIndex = 64 - startBitSubIndex;
                data[endIndex] = data[endIndex] >>> endBitSubIndex << endBitSubIndex | ((long) value & maxEntryValue) >> endBitSubIndex;
            }
        }

        return data;
    }
}
