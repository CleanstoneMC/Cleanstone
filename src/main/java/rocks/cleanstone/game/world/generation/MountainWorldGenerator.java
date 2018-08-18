package rocks.cleanstone.game.world.generation;

import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.block.state.mapping.BlockStateMapping;
import rocks.cleanstone.game.block.state.mapping.ModernBlockStateMapping;
import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.game.material.block.vanilla.VanillaBlockType;
import rocks.cleanstone.game.world.chunk.ArrayBlockDataTable;
import rocks.cleanstone.game.world.chunk.BlockDataTable;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.SimpleChunk;
import rocks.cleanstone.game.world.chunk.data.block.BlockDataStorage;
import rocks.cleanstone.game.world.generation.utils.NoiseGenerator;
import rocks.cleanstone.net.packet.enums.Dimension;
import rocks.cleanstone.net.packet.enums.LevelType;

import java.util.HashSet;

public class MountainWorldGenerator extends AbstractWorldGenerator {

    private final MaterialRegistry materialRegistry;
    private final NoiseGenerator noiseGenerator;
    private final BlockStateMapping<Integer> blockStateMapping = new ModernBlockStateMapping(BlockState.of(VanillaBlockType.STONE));


    public MountainWorldGenerator(MaterialRegistry materialRegistry, int seed) {
        super(Dimension.OVERWORLD, LevelType.DEFAULT, seed);
        this.materialRegistry = materialRegistry;

        noiseGenerator = new NoiseGenerator();
        noiseGenerator.SetNoiseType(NoiseGenerator.NoiseType.SimplexFractal);
        noiseGenerator.SetSeed(seed);
        noiseGenerator.SetFrequency(0.0125F);
        noiseGenerator.SetFractalOctaves(3);
        noiseGenerator.SetFractalGain(0.35F);
        noiseGenerator.SetFractalLacunarity(3.5f);
    }

    @Override
    public Chunk generateChunk(int chunkX, int chunkY) {

        BlockDataTable blockDataTable = new ArrayBlockDataTable(true);
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int height = getHeightAt((chunkX << 4) + x, (chunkY << 4) + z);
                blockDataTable.setBlock(x, height, z, ImmutableBlock.of(VanillaBlockType.GRASS));
                blockDataTable.setBlock(x, height - 1, z, ImmutableBlock.of(VanillaBlockType.DIRT));
                blockDataTable.setBlock(x, height - 2, z, ImmutableBlock.of(VanillaBlockType.DIRT));

                for (int y = 1; y < (height - 2); y++) {
                    blockDataTable.setBlock(x, y, z, ImmutableBlock.of(VanillaBlockType.STONE));
                }

                blockDataTable.setBlock(x, 0, z, ImmutableBlock.of(VanillaBlockType.BEDROCK));
                for (int y = 0; y < Chunk.HEIGHT; y++) {
                    blockDataTable.setSkyLight(x, y, z, (byte) 15);
                }
            }
        }
        BlockDataStorage blockDataStorage = new BlockDataStorage(blockDataTable, blockStateMapping);

        return new SimpleChunk(blockDataTable, blockDataStorage, new HashSet<>(), chunkX, chunkY);
    }

    public int getHeightAt(int x, int z) {
        return (int) Math.pow(((noiseGenerator.GetNoise(x, z) + 1.0) / 2.0) * 128.0, 1.0);
    }
}
