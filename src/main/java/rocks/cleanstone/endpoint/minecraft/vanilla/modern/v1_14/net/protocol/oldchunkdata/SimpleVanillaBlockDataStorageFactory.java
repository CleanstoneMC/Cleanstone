package rocks.cleanstone.endpoint.minecraft.vanilla.modern.v1_14.net.protocol.oldchunkdata;

import org.springframework.stereotype.Component;

import rocks.cleanstone.endpoint.minecraft.vanilla.modern.v1_14.net.protocol.oldchunkdata.section.BlockDataSection;
import rocks.cleanstone.storage.chunk.BlockDataStorage;

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
    public VanillaBlockDataStorage get(BlockDataStorage table, DirectPalette directPalette,
                                       boolean omitDirectPaletteLength, boolean writeNonAirBlockCount,
                                       boolean omitLighting) {
        return new VanillaBlockDataStorage(table, directPalette, omitDirectPaletteLength,
                writeNonAirBlockCount, omitLighting);
    }
}
