package rocks.cleanstone.endpoint.minecraft.vanilla.v1_14.net.protocol.oldchunkdata;

import org.springframework.stereotype.Component;

import javax.annotation.Nullable;

import rocks.cleanstone.storage.chunk.BlockDataStorage;

@Component
public class SimpleVanillaBlockDataCodecFactory implements VanillaBlockDataCodecFactory {

    private final VanillaBlockDataStorageFactory vanillaBlockDataStorageFactory;

    public SimpleVanillaBlockDataCodecFactory(VanillaBlockDataStorageFactory vanillaBlockDataStorageFactory) {
        this.vanillaBlockDataStorageFactory = vanillaBlockDataStorageFactory;
    }

    @Override
    public VanillaBlockDataCodec get(DirectPalette directPalette, boolean omitDirectPaletteLength,
                                     boolean writeHeightMap, boolean writeNonAirBlockCount,
                                     boolean omitLighting, @Nullable BlockDataStorage BlockDataStorage) {
        return new VanillaBlockDataCodec(vanillaBlockDataStorageFactory, directPalette,
                omitDirectPaletteLength, writeHeightMap, writeNonAirBlockCount, omitLighting, BlockDataStorage);
    }
}
