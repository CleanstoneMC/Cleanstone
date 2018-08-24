package rocks.cleanstone.game.world.generation;

import java.util.HashSet;

import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.material.block.vanilla.VanillaBlockType;
import rocks.cleanstone.game.world.chunk.ArrayBlockDataTable;
import rocks.cleanstone.game.world.chunk.BlockDataTable;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.SimpleChunk;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.DirectPalette;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.VanillaBlockDataStorage;
import rocks.cleanstone.game.world.generation.utils.NoiseGenerator;
import rocks.cleanstone.net.minecraft.protocol.v1_13.ProtocolBlockStateMapping;
import rocks.cleanstone.net.packet.enums.Dimension;
import rocks.cleanstone.net.packet.enums.LevelType;

public class MountainWorldGenerator extends AbstractWorldGenerator {

    private static final Block GRASS_BLOCK = ImmutableBlock.of(VanillaBlockType.GRASS_BLOCK);
    private static final Block DIRT = ImmutableBlock.of(VanillaBlockType.DIRT);
    private static final Block STONE = ImmutableBlock.of(VanillaBlockType.STONE);
    private static final Block BEDROCK = ImmutableBlock.of(VanillaBlockType.BEDROCK);

    private final NoiseGenerator noiseGenerator;

    public MountainWorldGenerator(int seed) {
        super(Dimension.OVERWORLD, LevelType.DEFAULT, seed);

        noiseGenerator = new NoiseGenerator();
        noiseGenerator.SetNoiseType(NoiseGenerator.NoiseType.SimplexFractal);
        noiseGenerator.SetSeed(seed);
        noiseGenerator.SetFrequency(0.0125F);
        noiseGenerator.SetFractalOctaves(3);
        noiseGenerator.SetFractalGain(0.35F);
        noiseGenerator.SetFractalLacunarity(3.5F);
    }

    @Override
    public Chunk generateChunk(int chunkX, int chunkY) {

        BlockDataTable blockDataTable = new ArrayBlockDataTable(true);
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int height = getHeightAt((chunkX << 4) + x, (chunkY << 4) + z);
                blockDataTable.setBlock(x, height, z, GRASS_BLOCK);
                blockDataTable.setBlock(x, height - 1, z, DIRT);
                blockDataTable.setBlock(x, height - 2, z, DIRT);

                for (int y = 1; y < (height - 2); y++) {
                    blockDataTable.setBlock(x, y, z, STONE);
                }

                blockDataTable.setBlock(x, 0, z, BEDROCK);
                for (int y = 0; y < Chunk.HEIGHT; y++) {
                    blockDataTable.setSkyLight(x, y, z, (byte) 15);
                }
            }
        }
        DirectPalette directPalette = new DirectPalette(new ProtocolBlockStateMapping(), 14);
        VanillaBlockDataStorage blockDataStorage = new VanillaBlockDataStorage(blockDataTable,
                directPalette, true);

        return new SimpleChunk(blockDataTable, blockDataStorage, new HashSet<>(), chunkX, chunkY);
    }

    public int getHeightAt(int x, int z) {
        return (int) Math.pow(((noiseGenerator.GetNoise(x, z) + 1.0) / 2.0) * 128.0, 1.0);
    }
}
