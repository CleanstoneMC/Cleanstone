package rocks.cleanstone.endpoint.minecraft.vanilla.modern.v1_14.net.protocol.oldchunkdata;

import rocks.cleanstone.endpoint.minecraft.vanilla.modern.v1_14.net.protocol.oldchunkdata.section.BlockDataSection;
import rocks.cleanstone.storage.chunk.BlockDataStorage;

public interface VanillaBlockDataStorageFactory {
    VanillaBlockDataStorage get(VanillaBlockDataStorage blockDataStorage);

    VanillaBlockDataStorage get(BlockDataSection[] sections, boolean hasSkyLight,
                                DirectPalette directPalette, boolean omitDirectPaletteLength,
                                boolean writeNonAirBlockCount, boolean omitLighting);

    VanillaBlockDataStorage get(BlockDataStorage table, DirectPalette directPalette,
                                boolean omitDirectPaletteLength, boolean writeNonAirBlockCount,
                                boolean omitLighting);
}
