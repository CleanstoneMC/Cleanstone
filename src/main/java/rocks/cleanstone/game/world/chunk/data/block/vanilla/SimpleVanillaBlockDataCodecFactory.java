package rocks.cleanstone.game.world.chunk.data.block.vanilla;

import org.springframework.stereotype.Component;

@Component
public class SimpleVanillaBlockDataCodecFactory implements VanillaBlockDataCodecFactory {

    private final VanillaBlockDataStorageFactory vanillaBlockDataStorageFactory;

    public SimpleVanillaBlockDataCodecFactory(VanillaBlockDataStorageFactory vanillaBlockDataStorageFactory) {
        this.vanillaBlockDataStorageFactory = vanillaBlockDataStorageFactory;
    }

    @Override
    public VanillaBlockDataCodec get(DirectPalette directPalette, boolean omitDirectPaletteLength,
                                     boolean writeHeightMap, boolean writeNonAirBlockCount, boolean omitLighting) {
        return new VanillaBlockDataCodec(vanillaBlockDataStorageFactory, directPalette,
                omitDirectPaletteLength, writeHeightMap, writeNonAirBlockCount, omitLighting);
    }
}
