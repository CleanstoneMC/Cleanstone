package rocks.cleanstone.endpoint.minecraft.vanilla.modern.v1_14.net.protocol.oldchunkdata;

import rocks.cleanstone.storage.chunk.BlockDataStorage;

import javax.annotation.Nullable;

public interface VanillaBlockDataCodecFactory {

    VanillaBlockDataCodec get(DirectPalette directPalette, boolean omitDirectPaletteLength,
                              boolean writeHeightMap, boolean writeNonAirBlockCount,
                              boolean omitLighting, @Nullable BlockDataStorage BlockDataStorage);
}
