package rocks.cleanstone.game.world.chunk.data.block.vanilla;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.ReferenceCountUtil;
import lombok.val;
import net.querz.nbt.CompoundTag;
import net.querz.nbt.LongArrayTag;
import net.querz.nbt.Tag;
import rocks.cleanstone.data.InOutCodec;
import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.material.block.vanilla.VanillaBlockType;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class VanillaBlockDataCodec implements InOutCodec<VanillaBlockDataStorage, ByteBuf> {

    private final VanillaBlockDataStorageFactory vanillaBlockDataStorageFactory;
    private final DirectPalette directPalette;
    private final boolean omitDirectPaletteLength;

    public VanillaBlockDataCodec(VanillaBlockDataStorageFactory vanillaBlockDataStorageFactory, DirectPalette directPalette, boolean omitDirectPaletteLength) {
        this.vanillaBlockDataStorageFactory = vanillaBlockDataStorageFactory;
        this.directPalette = directPalette;
        this.omitDirectPaletteLength = omitDirectPaletteLength;
    }

    private static long[] encodeHeightMap(int[] heightMap) {
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

        int[] motionBlocking = new int[16 * 16];

        ByteBuf dataBuf = Unpooled.buffer();
        for (int sectionY = 0; sectionY < Chunk.HEIGHT / BlockDataSection.HEIGHT; sectionY++) {

            BlockDataSection section = storage.getSection(sectionY);
            if (section != null) {
                int nonAirBlockCount = 0;
                for (int x = 0; x < 16; x++) {
                    for (int y = 0; y < 16; y++) {
                        for (int z = 0; z < 16; z++) {
                            final BlockState blockState = section.getBlockStateStorage().get(x, y, z);
                            if (blockState.getBlockType() != VanillaBlockType.AIR && blockState.getBlockType() != VanillaBlockType.CAVE_AIR && blockState.getBlockType() != VanillaBlockType.VOID_AIR) {
                                nonAirBlockCount++;
                            }

                            motionBlocking[x + z * 16] = y + sectionY * 16 + 2; // Should be +1 (top of the block) but +2 works :tm:
                        }
                    }
                }


                dataBuf.writeShort(nonAirBlockCount);


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

        // Encode NBT Heightmaps
        {
            LongArrayTag longArrayTag = new LongArrayTag(encodeHeightMap(motionBlocking));

            val compoundTag = new CompoundTag();
            compoundTag.put("MOTION_BLOCKING", longArrayTag);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                compoundTag.serializeValue(new DataOutputStream(byteArrayOutputStream), Tag.DEFAULT_MAX_DEPTH);
            } catch (IOException i) {

            }

            data.writeBytes(byteArrayOutputStream.toByteArray());
        }

        ByteBufUtils.writeVarInt(data, dataBuf.readableBytes());
        data.writeBytes(dataBuf);
        ReferenceCountUtil.release(dataBuf);
        return data;
    }
}
