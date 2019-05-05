package rocks.cleanstone.game.world.chunk.data.block.vanilla;

public interface VanillaBlockDataCodecFactory {

    VanillaBlockDataCodec get(DirectPalette directPalette, boolean omitDirectPaletteLength,
                              boolean writeHeightMap, boolean writeNonAirBlockCount, boolean omitLighting);
}
