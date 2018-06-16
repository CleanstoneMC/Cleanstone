package rocks.cleanstone.game.world.region.chunk.data.block;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Nullable;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.ReferenceCountUtil;
import rocks.cleanstone.game.block.BlockState;
import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.world.region.chunk.ArrayBlockDataTable;
import rocks.cleanstone.game.world.region.chunk.BlockDataTable;
import rocks.cleanstone.game.world.region.chunk.Chunk;
import rocks.cleanstone.game.world.region.chunk.data.ChunkData;
import rocks.cleanstone.game.world.region.chunk.data.block.vanilla.PaletteBlockStateStorage;
import rocks.cleanstone.net.utils.ByteBufUtils;

/**
 * Stores data about blocks (e.g. block states, block light, etc.) that can be converted into a byte stream or
 * BlockDataTable efficiently in multiple BlockDataSections
 */
public class BlockDataStorage extends ChunkData {

    private static final int SEC_HEIGHT = BlockDataSection.HEIGHT, SEC_WIDTH = BlockDataSection.WIDTH,
            SEC_AMNT = Chunk.HEIGHT / SEC_HEIGHT;

    private final Map<Integer, BlockDataSection> sectionMap = new HashMap<>();
    private final boolean hasSkyLight;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public BlockDataStorage(int chunkX, int chunkY, ByteBuf buf, boolean hasSkyLight) throws IOException {
        super(chunkX, chunkY);
        this.hasSkyLight = hasSkyLight;

        int primaryBitMask = ByteBufUtils.readVarInt(buf);
        int dataLength = ByteBufUtils.readVarInt(buf);
        for (int sectionY = 0; sectionY < SEC_AMNT; sectionY++) {
            if ((primaryBitMask & (1 << sectionY)) != 0) {
                BlockDataSection section = new BlockDataSection(buf, hasSkyLight);
                sectionMap.put(sectionY, section);
            }
        }
    }

    public BlockDataStorage(int chunkX, int chunkY, Map<Integer, BlockDataSection> sectionMap,
                            boolean hasSkyLight) {
        super(chunkX, chunkY);
        this.hasSkyLight = hasSkyLight;

        this.sectionMap.putAll(sectionMap);
    }

    public BlockDataStorage(int chunkX, int chunkY, BlockDataTable table) {
        super(chunkX, chunkY);

        for (int sectionY = 0; sectionY < SEC_AMNT; sectionY++) {
            AtomicBoolean isEmptyFlag = new AtomicBoolean();
            BlockStateStorage storage = new PaletteBlockStateStorage(table, sectionY, isEmptyFlag);
            if (isEmptyFlag.get()) continue;
            byte[][][] blockLight = new byte[SEC_WIDTH][SEC_WIDTH][SEC_HEIGHT],
                    skyLight = new byte[SEC_WIDTH][SEC_WIDTH][SEC_HEIGHT];

            for (int y = 0; y < SEC_HEIGHT; y++) {
                for (int z = 0; z < SEC_WIDTH; z++) {
                    for (int x = 0; x < SEC_WIDTH; x++) {
                        int chunkRelativeY = y + sectionY * SEC_HEIGHT;
                        blockLight[x][z][y] = table.getBlockLight(x, chunkRelativeY, z);
                        skyLight[x][z][y] = table.getSkyLight(x, chunkRelativeY, z);
                    }
                }
            }
            BlockDataSection section = new BlockDataSection(storage, blockLight,
                    skyLight, table.hasSkylight());
            sectionMap.put(sectionY, section);

        }
        this.hasSkyLight = table.hasSkylight();
    }

    @Override
    public void write(ByteBuf buf) {
        int primaryBitMask = 0;

        ByteBuf dataBuf = Unpooled.buffer();
        for (int sectionY = 0; sectionY < SEC_AMNT; sectionY++) {
            BlockDataSection section = sectionMap.get(sectionY);
            if (section != null) {
                section.write(dataBuf);
                primaryBitMask |= (1 << sectionY);
            }
        }
        for (int z = 0; z < Chunk.WIDTH; z++) {
            for (int x = 0; x < Chunk.WIDTH; x++) {
                dataBuf.writeByte(127);  // TODO write biome data
            }
        }

        ByteBufUtils.writeVarInt(buf, primaryBitMask);
        ByteBufUtils.writeVarInt(buf, dataBuf.readableBytes());
        buf.writeBytes(dataBuf);
        ReferenceCountUtil.release(dataBuf);
    }

    @Nullable
    public BlockDataSection getSection(int sectionY) {
        return sectionMap.get(sectionY);
    }

    private BlockDataSection getOrCreateSection(int y) {
        BlockDataSection section = getSection(y);
        if (section == null) {
            section = new BlockDataSection(hasSkyLight);
            sectionMap.put(y, section);
        }
        return section;
    }

    public void setBlockState(int x, int chunkY, int z, BlockState state) {
        int sectionY = chunkY / SEC_AMNT;
        int y = chunkY - sectionY * SEC_HEIGHT;
        BlockDataSection section = getOrCreateSection(sectionY);
        section.getBlockStateStorage().set(x, y, z, state);
    }

    public void setBlockLight(int x, int chunkY, int z, byte blockLight) {
        int sectionY = chunkY / SEC_AMNT;
        int y = chunkY - sectionY * SEC_HEIGHT;
        BlockDataSection section = getOrCreateSection(sectionY);
        section.getBlockLight()[x][z][y] = blockLight;
    }

    public void setSkyLight(int x, int chunkY, int z, byte skyLight) {
        if (!hasSkyLight) return;
        int sectionY = chunkY / SEC_AMNT;
        int y = chunkY - sectionY * SEC_HEIGHT;
        BlockDataSection section = getOrCreateSection(sectionY);
        section.getSkyLight()[x][z][y] = skyLight;
    }

    public BlockDataTable constructTable() {
        ArrayBlockDataTable table = new ArrayBlockDataTable(hasSkyLight);
        for (int sectionY = 0; sectionY < SEC_AMNT; sectionY++) {
            BlockDataSection section = sectionMap.get(sectionY);
            for (int y = 0; y < SEC_HEIGHT; y++) {
                for (int z = 0; z < SEC_WIDTH; z++) {
                    for (int x = 0; x < SEC_WIDTH; x++) {
                        int chunkRelativeY = y + sectionY * SEC_HEIGHT;
                        // TODO how to create BlockEntities?
                        table.setBlock(x, chunkRelativeY, z,
                                ImmutableBlock.of(section.getBlockStateStorage().get(x, y, z)));
                        table.setBlockLight(x, chunkRelativeY, z, section.getBlockLight()[x][z][y]);
                        if (hasSkyLight)
                            table.setSkyLight(x, chunkRelativeY, z, section.getSkyLight()[x][z][y]);
                    }
                }
            }
        }
        return table;
    }

    public boolean hasSkyLight() {
        return hasSkyLight;
    }
}
