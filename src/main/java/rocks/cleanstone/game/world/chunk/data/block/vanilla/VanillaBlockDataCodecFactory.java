package rocks.cleanstone.game.world.chunk.data.block.vanilla;

import javax.annotation.Nullable;

import rocks.cleanstone.game.world.chunk.BlockDataTable;

public interface VanillaBlockDataCodecFactory {

    VanillaBlockDataCodec get(DirectPalette directPalette, boolean omitDirectPaletteLength,
                              boolean writeHeightMap, boolean writeNonAirBlockCount,
                              boolean omitLighting, @Nullable BlockDataTable blockDataTable);
}
