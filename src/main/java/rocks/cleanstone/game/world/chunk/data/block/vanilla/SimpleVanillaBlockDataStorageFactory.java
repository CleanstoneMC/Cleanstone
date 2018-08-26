package rocks.cleanstone.game.world.chunk.data.block.vanilla;

import org.springframework.stereotype.Component;
import rocks.cleanstone.game.block.ImmutableBlockProvider;
import rocks.cleanstone.game.world.chunk.BlockDataTable;

import java.util.Map;

@Component
public class SimpleVanillaBlockDataStorageFactory implements VanillaBlockDataStorageFactory {
    private final ImmutableBlockProvider immutableBlockProvider;

    public SimpleVanillaBlockDataStorageFactory(ImmutableBlockProvider immutableBlockProvider) {
        this.immutableBlockProvider = immutableBlockProvider;
    }

    @Override
    public VanillaBlockDataStorage get(VanillaBlockDataStorage blockDataStorage) {
        return new VanillaBlockDataStorage(blockDataStorage, immutableBlockProvider);
    }

    @Override
    public VanillaBlockDataStorage get(Map<Integer, BlockDataSection> sectionMap, boolean hasSkyLight,
                                       DirectPalette directPalette, boolean omitDirectPaletteLength) {
        return new VanillaBlockDataStorage(sectionMap, hasSkyLight, directPalette, omitDirectPaletteLength, immutableBlockProvider);
    }

    @Override
    public VanillaBlockDataStorage get(BlockDataTable table, DirectPalette directPalette,
                                       boolean omitDirectPaletteLength) {
        return new VanillaBlockDataStorage(table, directPalette, omitDirectPaletteLength, immutableBlockProvider);
    }
}
