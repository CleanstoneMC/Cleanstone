package rocks.cleanstone.game.world.chunk.data.block.vanilla;

import rocks.cleanstone.game.world.chunk.BlockDataTable;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.section.BlockDataSection;

public interface VanillaBlockDataStorageFactory {
    VanillaBlockDataStorage get(VanillaBlockDataStorage blockDataStorage);

    VanillaBlockDataStorage get(BlockDataSection[] sectionMap, boolean hasSkyLight,
                                DirectPalette directPalette, boolean omitDirectPaletteLength);

    VanillaBlockDataStorage get(BlockDataTable table, DirectPalette directPalette,
                                boolean omitDirectPaletteLength);
}
