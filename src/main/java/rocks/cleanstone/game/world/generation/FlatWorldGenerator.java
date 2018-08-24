package rocks.cleanstone.game.world.generation;

import java.util.HashSet;

import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.material.block.vanilla.VanillaBlockType;
import rocks.cleanstone.game.world.chunk.ArrayBlockDataTable;
import rocks.cleanstone.game.world.chunk.BlockDataTable;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.SimpleChunk;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.DirectPalette;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.VanillaBlockDataStorage;
import rocks.cleanstone.net.minecraft.protocol.v1_13.ProtocolBlockStateMapping;

public class FlatWorldGenerator implements WorldGenerator {

    private final BlockDataTable blockDataTable;
    private final VanillaBlockDataStorage blockDataStorage;

    public FlatWorldGenerator() {
        blockDataTable = new ArrayBlockDataTable(true);
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                blockDataTable.setBlock(x, 45, z, ImmutableBlock.of(VanillaBlockType.GRASS));
                for (int y = 44; y > 0; y--) {
                    blockDataTable.setBlock(x, y, z, ImmutableBlock.of(VanillaBlockType.STONE));
                }

                blockDataTable.setBlock(x, 0, z, ImmutableBlock.of(VanillaBlockType.BEDROCK));
                for (int y = 0; y < Chunk.HEIGHT; y++) {
                    blockDataTable.setSkyLight(x, y, z, (byte) 15);
                }
            }
        }

        DirectPalette directPalette = new DirectPalette(new ProtocolBlockStateMapping(), 14);
        blockDataStorage = new VanillaBlockDataStorage(blockDataTable, directPalette, true);
    }

    @Override
    public Chunk generateChunk(int chunkX, int chunkY) {
        return new SimpleChunk(new ArrayBlockDataTable((ArrayBlockDataTable) blockDataTable), new
                VanillaBlockDataStorage(blockDataStorage), new HashSet<>(), chunkX, chunkY);
    }

    @Override
    public int getHeightAt(int x, int y) {
        return 45;
    }
}
