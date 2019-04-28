package rocks.cleanstone.net.minecraft.protocol.v1_14.outbound;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.val;
import net.querz.nbt.CompoundTag;
import net.querz.nbt.LongArrayTag;
import net.querz.nbt.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.block.state.mapping.BlockStateMapping;
import rocks.cleanstone.game.material.block.vanilla.VanillaBlockType;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.DirectPalette;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.VanillaBlockDataCodecFactory;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.VanillaBlockDataStorage;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.VanillaBlockDataStorageFactory;
import rocks.cleanstone.net.minecraft.packet.outbound.ChunkDataPacket;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Component
public class ChunkDataCodec implements OutboundPacketCodec<ChunkDataPacket> {

    private final BlockStateMapping<Integer> blockStateMapping;
    private final VanillaBlockDataStorageFactory vanillaBlockDataStorageFactory;
    private final VanillaBlockDataCodecFactory vanillaBlockDataCodecFactory;

    public ChunkDataCodec(@Qualifier("protocolBlockStateMapping_v1_14") BlockStateMapping<Integer> blockStateMapping, VanillaBlockDataStorageFactory vanillaBlockDataStorageFactory,
                          VanillaBlockDataCodecFactory vanillaBlockDataCodecFactory) {
        this.blockStateMapping = blockStateMapping;
        this.vanillaBlockDataStorageFactory = vanillaBlockDataStorageFactory;
        this.vanillaBlockDataCodecFactory = vanillaBlockDataCodecFactory;
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, ChunkDataPacket packet) throws IOException {
        byteBuf.writeInt(packet.getChunkX());
        byteBuf.writeInt(packet.getChunkZ());
        byteBuf.writeBoolean(packet.isGroundUpContinuous());

        DirectPalette directPalette = new DirectPalette(blockStateMapping, 14);
        VanillaBlockDataStorage storage = vanillaBlockDataStorageFactory.get(packet.getBlockDataTable(),
                directPalette, true);

        ByteBuf blockData = vanillaBlockDataCodecFactory.get(directPalette, true).encode(storage);

        ByteBuf newData = Unpooled.buffer();

        val primaryBitMask = ByteBufUtils.readVarInt(blockData);
        ByteBufUtils.writeVarInt(newData, primaryBitMask);

        int[] nonAirBlockCounts = new int[16];
        val table = storage.constructTable();
        int[] motionBlocking = new int[16 * 16];
        for (int sections = 0; sections < 16; sections++) {
            int nonAirBlockCount = 0;
            for (int x = 0; x < 16; x++) {
                for (int y = 0; y < 16; y++) {
                    for (int z = 0; z < 16; z++) {
                        final BlockState blockState = table.getBlock(x, y, z).getState();
                        if (blockState.getBlockType() != VanillaBlockType.AIR && blockState.getBlockType() != VanillaBlockType.CAVE_AIR && blockState.getBlockType() != VanillaBlockType.VOID_AIR) {
                            nonAirBlockCount++;
                        }

                        motionBlocking[x + z * 16] = y + sections * 16 + 2; // Should be +1 (top of the block) but +2 works :tm:
                    }
                }
            }

            nonAirBlockCounts[sections] = nonAirBlockCount;
        }

        // Encode NBT Heightmaps
        {
            LongArrayTag longArrayTag = new LongArrayTag(encodeHeightMap(motionBlocking));

            val compoundTag = new CompoundTag();
            compoundTag.put("MOTION_BLOCKING", longArrayTag);

            val encodingTag = new CompoundTag();
            encodingTag.put("", compoundTag);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                encodingTag.serializeValue(new DataOutputStream(byteArrayOutputStream), Tag.DEFAULT_MAX_DEPTH);
            } catch (IOException i) {

            }

            newData.writeBytes(byteArrayOutputStream.toByteArray());
        }

        val dataSize = ByteBufUtils.readVarInt(blockData);
        ByteBufUtils.writeVarInt(newData, dataSize);

        // Read Chunk Sections
        for (int i = 0; i < 16; i++) {
            newData.writeShort(nonAirBlockCounts[i]);

            val bitsPerBlock = blockData.readByte();
            newData.writeByte(bitsPerBlock);

            if (bitsPerBlock >= 9) {
                val paletteLength = ByteBufUtils.readVarInt(blockData);
                ByteBufUtils.writeVarInt(newData, paletteLength);

                // Read Palette
                for (int j = 0; j < paletteLength; j++) {
                    newData.writeLong(blockData.readLong());
                }
            }

            val dataLength = ByteBufUtils.readVarInt(blockData);
            ByteBufUtils.writeVarInt(newData, dataLength);

            // Read DataArray
            for (int j = 0; j < dataLength; j++) {
                newData.writeLong(blockData.readLong());
            }

            // Read BlockLight
            for (int j = 0; j < 16; j++) {
                blockData.readByte();
            }

            if (storage.getSection(i).hasSkyLight()) {
                // Read SkyLight
                for (int j = 0; j < 16; j++) {
                    blockData.readByte();
                }
            }
        }

        newData.writeInt(127);

        byteBuf.writeBytes(newData);
        blockData.release();
        newData.release();

        ByteBufUtils.writeVarInt(byteBuf, 0);
        //TODO encode NBT Tag array of block entities
        //ByteBufUtils.writeVarInt(byteBuf,chunkDataPacket.getBlockEntities().length);
        //byteBuf.writeBytes(chunkDataPacket.getBlockEntities())

        return byteBuf;
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
}
