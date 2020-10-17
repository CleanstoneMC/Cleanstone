package rocks.cleanstone.game.world.generation;

import org.springframework.stereotype.Component;
import rocks.cleanstone.endpoint.minecraft.vanilla.block.VanillaBlockType;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.enums.Dimension;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.enums.LevelType;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.entity.RotatablePosition;
import rocks.cleanstone.game.entity.Rotation;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.ChunkCoords;
import rocks.cleanstone.game.world.chunk.SimpleChunk;
import rocks.cleanstone.game.world.generation.utils.NoiseGenerator;
import rocks.cleanstone.storage.chunk.BlockDataStorage;
import rocks.cleanstone.storage.chunk.BlockDataStorageProvider;
import rocks.cleanstone.storage.engine.rocksdb.entity.EntityData;

import java.util.HashSet;

import static rocks.cleanstone.game.world.generation.WorldGenerationParameter.*;

@Component
public class MountainWorldGenerator extends AbstractWorldGenerator {

    private final BlockDataStorageProvider blockDataStorageProvider;
    private final Block GRASS_BLOCK;
    private final Block DIRT;
    private final Block STONE;
    private final Block BEDROCK;
    private final NoiseGenerator noiseGenerator;

    public MountainWorldGenerator(BlockDataStorageProvider blockDataStorageProvider) {
        super(Dimension.OVERWORLD, LevelType.DEFAULT);
        this.blockDataStorageProvider = blockDataStorageProvider;

        noiseGenerator = new NoiseGenerator();
        noiseGenerator.SetNoiseType(NoiseGenerator.NoiseType.SimplexFractal);
        noiseGenerator.SetFractalType(NoiseGenerator.FractalType.CUSTOM);

        setGenerationParameter(FREQUENCY, 0.01F);
        setGenerationParameter(FRACTAL_OCTAVES, 4);
        setFractalGenerationParameter(FractalWorldGenerationParameter.AMPLITUDE, 1, 0.75F);
        setFractalGenerationParameter(FractalWorldGenerationParameter.FREQUENCY, 1, 0.3F);
        setFractalGenerationParameter(FractalWorldGenerationParameter.AMPLITUDE, 2, 0.25F);
        setFractalGenerationParameter(FractalWorldGenerationParameter.FREQUENCY, 2, 1F);
        setFractalGenerationParameter(FractalWorldGenerationParameter.AMPLITUDE, 3, 0.1F);
        setFractalGenerationParameter(FractalWorldGenerationParameter.FREQUENCY, 3, 2F);
        setFractalGenerationParameter(FractalWorldGenerationParameter.AMPLITUDE, 4, 0.1F);
        setFractalGenerationParameter(FractalWorldGenerationParameter.FREQUENCY, 4, 4F);
        setGenerationParameter(AMPLITUDE, 128F);
        setGenerationParameter(HEIGHT, 25);
        setGenerationParameter(POWER, 1F);

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
        }
    }

    @Override
    public void setFractalGenerationParameter(FractalWorldGenerationParameter parameter, int octave,
                                              double value) {
        super.setFractalGenerationParameter(parameter, octave, value);
        switch (parameter) {
            case AMPLITUDE:
                noiseGenerator.SetCustomFractalAmplitude(octave, (float) value);
                break;
            case FREQUENCY:
                noiseGenerator.SetCustomFractalFrequency(octave, (float) value);
                break;

        }
    }

    @Override
    public Chunk generateChunk(int seed, ChunkCoords coords) {
        noiseGenerator.SetSeed(seed);
        BlockDataStorage blockDataStorage = blockDataStorageProvider.getBlockDataStorage();

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int height = getHeightAt(seed, (coords.getX() << 4) + x, (coords.getZ() << 4) + z);
                blockDataStorage.setBlock(x, height, z, GRASS_BLOCK);
                blockDataStorage.setBlock(x, height - 1, z, DIRT);
                blockDataStorage.setBlock(x, height - 2, z, DIRT);

                for (int y = 1; y < (height - 2); y++) {
                    blockDataStorage.setBlock(x, y, z, STONE);
                }

                blockDataStorage.setBlock(x, 0, z, BEDROCK);
                for (int y = 0; y < Chunk.HEIGHT; y++) {
                    blockDataStorage.setSkyLight(x, y, z, (byte) 15);
                }
            }
        }

        return new SimpleChunk(blockDataStorage, new EntityData(new HashSet<>()), coords);
    }

    @Override
    public RotatablePosition getFirstSpawnPosition(int seed) {
        noiseGenerator.SetSeed(seed);
        return new RotatablePosition(new Position(0, getHeightAt(seed, 0, 0) + 1, 0), new Rotation(0, 0));
    }

    private int getHeightAt(int seed, int blockX, int blockZ) {
        double amplitude = getGenerationParameter(AMPLITUDE);
        double power = getGenerationParameter(POWER);
        int lowestY = (int) getGenerationParameter(HEIGHT);
        int height = (int) Math.pow(
                ((noiseGenerator.GetNoise(blockX, blockZ) + 1.0) / 2.0) * amplitude,
                power) + lowestY;
        return Math.max(0, Math.min(255, height));
    }

    @Override
    public String getName() {
        return "mountainWorldGenerator";
    }
}
