package rocks.cleanstone.game.world.chunk.data.block.vanilla;

import org.springframework.stereotype.Component;

import rocks.cleanstone.game.world.chunk.BlockDataTable;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.section.BlockDataSection;

@Component
public class SimpleVanillaBlockDataStorageFactory implements VanillaBlockDataStorageFactory {

    @Override
    public VanillaBlockDataStorage get(VanillaBlockDataStorage blockDataStorage) {
        return new VanillaBlockDataStorage(blockDataStorage);
    }

    @Override
    public VanillaBlockDataStorage get(BlockDataSection[] sections, boolean hasSkyLight,
                                       DirectPalette directPalette, boolean omitDirectPaletteLength,
                                       boolean writeNonAirBlockCount, boolean omitLighting) {
        return new VanillaBlockDataStorage(sections, hasSkyLight, directPalette, omitDirectPaletteLength,
                writeNonAirBlockCount, omitLighting);
    }

    @Override
    public VanillaBlockDataStorage get(BlockDataTable table, DirectPalette directPalette,
                                       boolean omitDirectPaletteLength, boolean writeNonAirBlockCount,
                                       boolean omitLighting) {
        return new VanillaBlockDataStorage(table, directPalette, omitDirectPaletteLength,
                writeNonAirBlockCount, omitLighting);
    }
}
