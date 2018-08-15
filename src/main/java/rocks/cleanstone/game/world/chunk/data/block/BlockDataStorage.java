package rocks.cleanstone.game.world.chunk.data.block;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.block.state.mapping.BlockStateMapping;
import rocks.cleanstone.game.world.chunk.ArrayBlockDataTable;
import rocks.cleanstone.game.world.chunk.BlockDataTable;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.PaletteBlockStateStorage;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Stores data about blocks (e.g. block states, block light, etc.) that can be converted into a byte stream or
 * BlockDataTable efficiently in multiple BlockDataSections
 */
public class BlockDataStorage {

    private static final int SEC_HEIGHT = BlockDataSection.HEIGHT, SEC_WIDTH = BlockDataSection.WIDTH,
            SEC_AMNT = Chunk.HEIGHT / SEC_HEIGHT;

    private final Map<Integer, BlockDataSection> sectionMap = new HashMap<>();
    private final boolean hasSkyLight;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final BlockStateMapping<Integer> blockStateMapping;

    public BlockDataStorage(BlockDataStorage blockDataStorage, BlockStateMapping<Integer> blockStateMapping) {
        this.blockStateMapping = blockStateMapping;
        hasSkyLight = blockDataStorage.hasSkyLight;

        blockDataStorage.sectionMap.forEach((integer, blockDataSection) -> {
            sectionMap.put(integer, new BlockDataSection(blockDataSection, blockStateMapping));
        });
    }

    public BlockDataStorage(Map<Integer, BlockDataSection> sectionMap, boolean hasSkyLight,
                            BlockStateMapping<Integer> blockStateMapping) {
        this.hasSkyLight = hasSkyLight;
        this.blockStateMapping = blockStateMapping;
        this.sectionMap.putAll(sectionMap);
    }

    public BlockDataStorage(BlockDataTable table, BlockStateMapping<Integer> blockStateMapping) {
        this.blockStateMapping = blockStateMapping;

        for (int sectionY = 0; sectionY < SEC_AMNT; sectionY++) {
            AtomicBoolean isEmptyFlag = new AtomicBoolean();
            BlockStateStorage storage = new PaletteBlockStateStorage(table, sectionY, isEmptyFlag,
                    blockStateMapping);
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

    @Nullable
    public BlockDataSection getSection(int sectionY) {
        return sectionMap.get(sectionY);
    }

    private BlockDataSection getOrCreateSection(int y) {
        BlockDataSection section = getSection(y);
        if (section == null) {
            section = new BlockDataSection(hasSkyLight, blockStateMapping);
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
            if (section != null)
                for (int y = 0; y < SEC_HEIGHT; y++) {
                    for (int z = 0; z < SEC_WIDTH; z++) {
                        for (int x = 0; x < SEC_WIDTH; x++) {
                            int chunkRelativeY = y + sectionY * SEC_HEIGHT;
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

    public Map<Integer, BlockDataSection> getSectionMap() {
        return sectionMap;
    }
}
