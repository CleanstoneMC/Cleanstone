package rocks.cleanstone.game.world.generation;

import org.springframework.stereotype.Component;
import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.material.block.vanilla.VanillaBlockType;
import rocks.cleanstone.game.world.chunk.ArrayBlockDataTable;
import rocks.cleanstone.game.world.chunk.BlockDataTable;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.SimpleChunk;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.DirectPalette;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.VanillaBlockDataStorage;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.VanillaBlockDataStorageFactory;
import rocks.cleanstone.game.world.chunk.data.entity.EntityData;
import rocks.cleanstone.game.world.generation.utils.NoiseGenerator;
import rocks.cleanstone.net.minecraft.packet.enums.Dimension;
import rocks.cleanstone.net.minecraft.packet.enums.LevelType;
import rocks.cleanstone.net.minecraft.protocol.v1_13_1.ProtocolBlockStateMapping_v1_13_1;

import java.util.HashSet;

import static rocks.cleanstone.game.world.generation.WorldGenerationParameter.*;

@Component
public class MountainWorldGenerator extends AbstractWorldGenerator {

    private Block GRASS_BLOCK;
    private Block DIRT;
    private Block STONE;
    private Block BEDROCK;
    private NoiseGenerator noiseGenerator;
    private final VanillaBlockDataStorageFactory vanillaBlockDataStorageFactory;
    private final ProtocolBlockStateMapping_v1_13_1 blockStateMapping;

    public MountainWorldGenerator(
            VanillaBlockDataStorageFactory vanillaBlockDataStorageFactory,
            ProtocolBlockStateMapping_v1_13_1 blockStateMapping

    ) {
        super(Dimension.OVERWORLD, LevelType.DEFAULT);
        this.vanillaBlockDataStorageFactory = vanillaBlockDataStorageFactory;
        this.blockStateMapping = blockStateMapping;

        noiseGenerator = new NoiseGenerator();
        noiseGenerator.SetNoiseType(NoiseGenerator.NoiseType.SimplexFractal);

        setGenerationParameter(FREQUENCY, 0.0125F);
        setGenerationParameter(FRACTAL_OCTAVES, 3);
        setGenerationParameter(FRACTAL_GAIN, 0.35F);
        setGenerationParameter(FRACTAL_LACUNARITY, 3.5F);

        GRASS_BLOCK = ImmutableBlock.of(VanillaBlockType.GRASS_BLOCK);
        DIRT = ImmutableBlock.of(VanillaBlockType.DIRT);
        STONE = ImmutableBlock.of(VanillaBlockType.STONE);
        BEDROCK = ImmutableBlock.of(VanillaBlockType.BEDROCK);
    }


    @Override
    public void setGenerationParameter(WorldGenerationParameter parameter, double value) {
        super.setGenerationParameter(parameter, value);

        switch (parameter) {
            case FREQUENCY:
                noiseGenerator.SetFrequency((float) value);
                break;
            case FRACTAL_OCTAVES:
                noiseGenerator.SetFractalOctaves((int) value);
                break;
            case FRACTAL_GAIN:
                noiseGenerator.SetFractalGain((float) value);
                break;
            case FRACTAL_LACUNARITY:
                noiseGenerator.SetFractalLacunarity((float) value);
                break;
        }
    }

    @Override
    public Chunk generateChunk(int seed, int chunkX, int chunkZ) {
        noiseGenerator.SetSeed(seed);
        BlockDataTable blockDataTable = new ArrayBlockDataTable(true);
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int height = getHeightAt(seed, (chunkX << 4) + x, (chunkZ << 4) + z);
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
        DirectPalette directPalette = new DirectPalette(blockStateMapping, 14);
        VanillaBlockDataStorage blockDataStorage = vanillaBlockDataStorageFactory.get(blockDataTable,
                directPalette, true);

        return new SimpleChunk(blockDataTable, blockDataStorage, new EntityData(new HashSet<>()), chunkX, chunkZ);
    }

    public int getHeightAt(int seed, int x, int z) {
        noiseGenerator.SetSeed(seed);
        return (int) Math.pow(((noiseGenerator.GetNoise(x, z) + 1.0) / 2.0) * 128.0, 1.0);
    }

    @Override
    public String getName() {
        return "mountainWorldGenerator";
    }
}
