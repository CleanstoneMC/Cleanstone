package rocks.cleanstone.game.world.generation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.material.block.vanilla.VanillaBlockType;
import rocks.cleanstone.game.world.chunk.ArrayBlockDataTable;
import rocks.cleanstone.game.world.chunk.BlockDataTable;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.SimpleChunk;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.DirectPalette;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.VanillaBlockDataStorage;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.VanillaBlockDataStorageFactory;
import rocks.cleanstone.net.minecraft.protocol.v1_13.ProtocolBlockStateMapping;

import java.util.HashSet;

@Component("flatWorldGenerator")
@Scope("prototype")
public class FlatWorldGenerator implements WorldGenerator {

    private boolean initialized = false;
    private BlockDataTable blockDataTable;
    private VanillaBlockDataStorage blockDataStorage;
    private final VanillaBlockDataStorageFactory vanillaBlockDataStorageFactory;

    @Autowired
    public FlatWorldGenerator(
            VanillaBlockDataStorageFactory vanillaBlockDataStorageFactory
    ) {
        this.vanillaBlockDataStorageFactory = vanillaBlockDataStorageFactory;
    }

    @Override
    public Chunk generateChunk(int seed, int chunkX, int chunkY) {
        if (!initialized) {
            initialize();
        }

        return new SimpleChunk(new ArrayBlockDataTable((ArrayBlockDataTable) blockDataTable),
                vanillaBlockDataStorageFactory.get(blockDataStorage), new HashSet<>(), chunkX, chunkY);
    }

    private void initialize() {
        blockDataTable = new ArrayBlockDataTable(true);
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                blockDataTable.setBlock(x, 45, z, ImmutableBlock.of(VanillaBlockType.GRASS_BLOCK));
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
        blockDataStorage = vanillaBlockDataStorageFactory.get(blockDataTable, directPalette, true);

        initialized = true;
    }

    @Override
    public int getHeightAt(int seed, int x, int y) {
        return 45;
    }

    @Override
    public String getName() {
        return "flatWorldGenerator";
    }
}
