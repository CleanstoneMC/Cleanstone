package rocks.cleanstone.game.world.chunk.data.block.vanilla;

import com.github.steveice10.opennbt.NBTIO;
import com.github.steveice10.opennbt.tag.builtin.CompoundTag;
import com.github.steveice10.opennbt.tag.builtin.LongArrayTag;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.annotation.Nullable;

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

import static rocks.cleanstone.game.world.chunk.data.block.vanilla.section.BlockDataSection.*;

@Slf4j
public class VanillaBlockDataCodec implements InOutCodec<VanillaBlockDataStorage, ByteBuf> {

    private final VanillaBlockDataStorageFactory vanillaBlockDataStorageFactory;
    private final DirectPalette directPalette;
    private final boolean omitDirectPaletteLength, writeHeightMap, writeNonAirBlockCount, omitLighting;
    @Nullable
    private final BlockDataTable blockDataTable;

    public VanillaBlockDataCodec(VanillaBlockDataStorageFactory vanillaBlockDataStorageFactory,
                                 DirectPalette directPalette, boolean omitDirectPaletteLength,
                                 boolean writeHeightMap, boolean writeNonAirBlockCount,
                                 boolean omitLighting, @Nullable BlockDataTable blockDataTable) {
        this.vanillaBlockDataStorageFactory = vanillaBlockDataStorageFactory;
        this.directPalette = directPalette;
        this.omitDirectPaletteLength = omitDirectPaletteLength;
        this.omitLighting = omitLighting;
        this.writeHeightMap = writeHeightMap;
        this.writeNonAirBlockCount = writeNonAirBlockCount;
        this.blockDataTable = blockDataTable;
    }

    @Override
    public VanillaBlockDataStorage decode(ByteBuf data) throws IOException {
        BlockDataSection[] sections = new BlockDataSection[Chunk.HEIGHT / HEIGHT];
        int primaryBitMask = ByteBufUtils.readVarInt(data);
        int dataSize = ByteBufUtils.readVarInt(data);
        for (int sectionY = 0; sectionY < Chunk.HEIGHT / HEIGHT; sectionY++) {
            if ((primaryBitMask & (1 << sectionY)) != 0) {
                BlockDataSection section = new BlockDataSection(data, true, directPalette,
                        omitDirectPaletteLength, writeNonAirBlockCount, omitLighting);
                sections[sectionY] = section;
            }
        }
        return vanillaBlockDataStorageFactory.get(sections, true, directPalette, omitDirectPaletteLength,
                writeNonAirBlockCount, omitLighting);
    }

    @Override
    public ByteBuf encode(VanillaBlockDataStorage storage) {
        ByteBuf data = Unpooled.buffer();
        int primaryBitMask = 0;

        ByteBuf dataBuf = Unpooled.buffer();
        for (int sectionY = 0; sectionY < Chunk.HEIGHT / HEIGHT; sectionY++) {
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
        BlockDataTable table = blockDataTable != null ? blockDataTable : storage.constructTable();
        int[] motionBlocking = new int[WIDTH * WIDTH];
        for (int sectionY = 0; sectionY < Chunk.HEIGHT / HEIGHT; sectionY++) {
            for (int x = 0; x < WIDTH; x++) {
                for (int y = 0; y < HEIGHT; y++) {
                    for (int z = 0; z < WIDTH; z++) {
                        BlockState blockState = table.getBlock(x, y + sectionY * HEIGHT, z).getState();
                        if (blockState.getBlockType() != VanillaBlockType.AIR)
                            motionBlocking[x + z * 16] = y + sectionY * 16 + 2;
                    }
                }
            }
        }
        CompoundTag heightMap = new CompoundTag("");
        heightMap.put(new LongArrayTag("MOTION_BLOCKING", HeightMapUtil.encodeHeightMap(motionBlocking)));
        try {
            ByteBufOutputStream byteBufStream = new ByteBufOutputStream(data);
            DataOutputStream dataOutputStream = new DataOutputStream(byteBufStream);
            NBTIO.writeTag((DataOutput) dataOutputStream, heightMap);
            dataOutputStream.close();
        } catch (IOException e) {
            log.error("Error occurred while serializing heightMap NBT data", e);
        }
    }
}
