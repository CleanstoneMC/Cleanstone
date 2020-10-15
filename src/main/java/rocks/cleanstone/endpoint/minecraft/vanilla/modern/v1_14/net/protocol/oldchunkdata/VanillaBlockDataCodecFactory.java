package rocks.cleanstone.endpoint.minecraft.vanilla.modern.v1_14.net.protocol.oldchunkdata;

import javax.annotation.Nullable;

import rocks.cleanstone.storage.chunk.BlockDataStorage;

public interface VanillaBlockDataCodecFactory {

    VanillaBlockDataCodec get(DirectPalette directPalette, boolean omitDirectPaletteLength,
                              boolean writeHeightMap, boolean writeNonAirBlockCount,
                              boolean omitLighting, @Nullable BlockDataStorage BlockDataStorage);
}
