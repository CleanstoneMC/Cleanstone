package rocks.cleanstone.game.world.chunk.data.block.vanilla;

import org.springframework.stereotype.Component;
import rocks.cleanstone.game.world.chunk.BlockDataTable;

import java.util.Map;

@Component
public class SimpleVanillaBlockDataStorageFactory implements VanillaBlockDataStorageFactory {

    @Override
    public VanillaBlockDataStorage get(VanillaBlockDataStorage blockDataStorage) {
        return new VanillaBlockDataStorage(blockDataStorage);
    }

    @Override
    public VanillaBlockDataStorage get(Map<Integer, BlockDataSection> sectionMap, boolean hasSkyLight,
                                       DirectPalette directPalette, boolean omitDirectPaletteLength) {
        return new VanillaBlockDataStorage(sectionMap, hasSkyLight, directPalette, omitDirectPaletteLength);
    }

    @Override
    public VanillaBlockDataStorage get(BlockDataTable table, DirectPalette directPalette,
                                       boolean omitDirectPaletteLength) {
        return new VanillaBlockDataStorage(table, directPalette, omitDirectPaletteLength);
    }
}
