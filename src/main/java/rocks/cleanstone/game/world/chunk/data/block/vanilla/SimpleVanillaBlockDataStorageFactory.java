package rocks.cleanstone.game.world.chunk.data.block.vanilla;

import org.springframework.stereotype.Component;
import rocks.cleanstone.game.world.chunk.BlockDataTable;

@Component
public class SimpleVanillaBlockDataStorageFactory implements VanillaBlockDataStorageFactory {

    @Override
    public VanillaBlockDataStorage get(VanillaBlockDataStorage blockDataStorage) {
        return new VanillaBlockDataStorage(blockDataStorage);
    }

    @Override
    public VanillaBlockDataStorage get(BlockDataSection[] sections, boolean hasSkyLight,
                                       DirectPalette directPalette, boolean omitDirectPaletteLength) {
        return new VanillaBlockDataStorage(sections, hasSkyLight, directPalette, omitDirectPaletteLength);
    }

    @Override
    public VanillaBlockDataStorage get(BlockDataTable table, DirectPalette directPalette,
                                       boolean omitDirectPaletteLength) {
        return new VanillaBlockDataStorage(table, directPalette, omitDirectPaletteLength);
    }
}
