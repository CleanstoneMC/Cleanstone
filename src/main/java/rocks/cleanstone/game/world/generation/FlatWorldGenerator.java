package rocks.cleanstone.game.world.generation;

import java.util.HashSet;

import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.material.VanillaMaterial;
import rocks.cleanstone.game.world.region.chunk.ArrayBlockDataTable;
import rocks.cleanstone.game.world.region.chunk.BlockDataTable;
import rocks.cleanstone.game.world.region.chunk.Chunk;
import rocks.cleanstone.game.world.region.chunk.SimpleChunk;
import rocks.cleanstone.game.world.region.chunk.data.block.BlockDataStorage;

public class FlatWorldGenerator implements WorldGenerator {

    private final BlockDataTable blockDataTable;
    private final BlockDataStorage blockDataStorage;

    public FlatWorldGenerator() {
        blockDataTable = new ArrayBlockDataTable(true);
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                blockDataTable.setBlock(x, 45, z, ImmutableBlock.of(VanillaMaterial.GRASS));
                for (int y = 44; y > 0; y--) {
                    blockDataTable.setBlock(x, y, z, ImmutableBlock.of(VanillaMaterial.STONE));
                }

                blockDataTable.setBlock(x, 0, z, ImmutableBlock.of(VanillaMaterial.BEDROCK));
                for (int y = 0; y < Chunk.HEIGHT; y++) {
                    blockDataTable.setSkyLight(x, y, z, (byte) 15);
                }
            }
        }
        blockDataStorage = new BlockDataStorage(blockDataTable);
    }

    @Override
    public Chunk generateChunk(int chunkX, int chunkY) {
        return new SimpleChunk(blockDataTable, blockDataStorage, new HashSet<>(), chunkX, chunkY);
    }
}
