package rocks.cleanstone.game.world.chunk.data.block.vanilla;

import com.google.common.base.Objects;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Nullable;

import lombok.extern.slf4j.Slf4j;
import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.material.block.vanilla.VanillaBlockType;
import rocks.cleanstone.game.world.chunk.ArrayBlockDataTable;
import rocks.cleanstone.game.world.chunk.BlockDataTable;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.data.block.BlockDataStorage;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.section.BlockDataSection;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.section.PaletteBlockStateStorage;

@Slf4j
public class VanillaBlockDataStorage implements BlockDataStorage {
    private static final int SEC_HEIGHT = BlockDataSection.HEIGHT, SEC_WIDTH = BlockDataSection.WIDTH,
            SEC_AMNT = Chunk.HEIGHT / SEC_HEIGHT;
    private final boolean hasSkyLight;
    private final DirectPalette directPalette;
    private final boolean omitDirectPaletteLength;
    private BlockDataSection[] sections = new BlockDataSection[SEC_AMNT];

    VanillaBlockDataStorage(VanillaBlockDataStorage blockDataStorage) {
        this.hasSkyLight = blockDataStorage.hasSkyLight;
        this.directPalette = blockDataStorage.directPalette;
        this.omitDirectPaletteLength = blockDataStorage.omitDirectPaletteLength;

        for (int i = 0; i < SEC_AMNT; i++) {
            sections[i] = new BlockDataSection(blockDataStorage.sections[i]);
        }
    }

    VanillaBlockDataStorage(BlockDataSection[] sections, boolean hasSkyLight,
                            DirectPalette directPalette, boolean omitDirectPaletteLength) {
        this.sections = Arrays.copyOf(sections, SEC_AMNT);
        this.hasSkyLight = hasSkyLight;
        this.directPalette = directPalette;
        this.omitDirectPaletteLength = omitDirectPaletteLength;
    }

    public VanillaBlockDataStorage(BlockDataTable table, DirectPalette directPalette,
                                   boolean omitDirectPaletteLength) {

        for (int sectionY = 0; sectionY < SEC_AMNT; sectionY++) {
            AtomicBoolean isEmptyFlag = new AtomicBoolean();
            if (isEmptyFlag.get()) {
                continue;
            }
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
            PaletteBlockStateStorage storage = new PaletteBlockStateStorage(table, sectionY, isEmptyFlag,
                    directPalette, omitDirectPaletteLength);

            BlockDataSection section = new BlockDataSection(storage, blockLight,
                    skyLight, table.hasSkylight());
            sections[sectionY] = section;

        }
        this.hasSkyLight = table.hasSkylight();
        this.directPalette = directPalette;
        this.omitDirectPaletteLength = omitDirectPaletteLength;
    }

    @Nullable
    public BlockDataSection getSection(int sectionY) {
        return sections[sectionY];
    }

    private BlockDataSection getOrCreateSection(int y) {
        BlockDataSection section = getSection(y);
        if (section == null) {
            section = new BlockDataSection(hasSkyLight, directPalette, omitDirectPaletteLength);
            sections[y] = section;
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
        if (!hasSkyLight) {
            return;
        }
        int sectionY = chunkY / SEC_AMNT;
        int y = chunkY - sectionY * SEC_HEIGHT;
        BlockDataSection section = getOrCreateSection(sectionY);
        section.getSkyLight()[x][z][y] = skyLight;
    }

    public BlockDataTable constructTable() {
        ArrayBlockDataTable table = new ArrayBlockDataTable(hasSkyLight);
        for (int sectionY = 0; sectionY < SEC_AMNT; sectionY++) {
            BlockDataSection section = sections[sectionY];
            if (section != null) {
                for (int y = 0; y < SEC_HEIGHT; y++) {
                    for (int z = 0; z < SEC_WIDTH; z++) {
                        for (int x = 0; x < SEC_WIDTH; x++) {
                            int chunkRelativeY = y + sectionY * SEC_HEIGHT;
                            BlockState state = section.getBlockStateStorage().get(x, y, z);
                            if (state.getBlockType() != VanillaBlockType.AIR) {
                                table.setBlock(x, chunkRelativeY, z, ImmutableBlock.of(state));
                            }
                            table.setBlockLight(x, chunkRelativeY, z, section.getBlockLight()[x][z][y]);
                            if (hasSkyLight) {
                                table.setSkyLight(x, chunkRelativeY, z, section.getSkyLight()[x][z][y]);
                            }
                        }
                    }
                }
            }
        }
        return table;
    }

    public boolean hasSkyLight() {
        return hasSkyLight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VanillaBlockDataStorage)) {
            return false;
        }
        VanillaBlockDataStorage that = (VanillaBlockDataStorage) o;
        return hasSkyLight == that.hasSkyLight &&
                omitDirectPaletteLength == that.omitDirectPaletteLength &&
                Objects.equal(sections, that.sections) &&
                Objects.equal(directPalette, that.directPalette);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(sections, hasSkyLight, directPalette, omitDirectPaletteLength);
    }
}
