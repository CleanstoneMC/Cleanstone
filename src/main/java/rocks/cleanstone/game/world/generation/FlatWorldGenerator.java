package rocks.cleanstone.game.world.generation;

import java.util.HashSet;

import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.material.VanillaMaterial;
import rocks.cleanstone.game.world.region.chunk.ArrayChunkTable;
import rocks.cleanstone.game.world.region.chunk.Chunk;
import rocks.cleanstone.game.world.region.chunk.ChunkTable;
import rocks.cleanstone.game.world.region.chunk.SimpleChunk;

public class FlatWorldGenerator implements WorldGenerator {

    private final ChunkTable chunkTable;

    public FlatWorldGenerator() {
        chunkTable = new ArrayChunkTable();
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                chunkTable.setBlock(x, 45, z, ImmutableBlock.of(VanillaMaterial.GRASS));

                for (int y = 44; y > 0; y--) {
                    chunkTable.setBlock(x, y, z, ImmutableBlock.of(VanillaMaterial.STONE));
                }

                chunkTable.setBlock(x, 0, z, ImmutableBlock.of(VanillaMaterial.BEDROCK));
            }
        }
    }

    @Override
    public Chunk generateChunk(int chunkX, int chunkY) {
        return new SimpleChunk(new HashSet<>(), chunkTable, chunkX, chunkY);
    }
}
