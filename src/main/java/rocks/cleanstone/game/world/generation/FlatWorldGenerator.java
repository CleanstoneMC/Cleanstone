package rocks.cleanstone.game.world.generation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocks.cleanstone.endpoint.minecraft.java.net.packet.enums.Dimension;
import rocks.cleanstone.endpoint.minecraft.java.net.packet.enums.LevelType;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.entity.RotatablePosition;
import rocks.cleanstone.game.entity.Rotation;
import rocks.cleanstone.game.material.block.vanilla.VanillaBlockType;
import rocks.cleanstone.game.world.chunk.Chunk;
import rocks.cleanstone.game.world.chunk.ChunkCoords;
import rocks.cleanstone.game.world.chunk.SimpleChunk;
import rocks.cleanstone.storage.chunk.BlockDataStorage;
import rocks.cleanstone.storage.chunk.BlockDataStorageProvider;
import rocks.cleanstone.storage.engine.leveldb.entity.EntityData;

import java.util.HashSet;

@Component("flatWorldGenerator")
public class FlatWorldGenerator extends AbstractWorldGenerator {

    private final BlockDataStorageProvider blockDataStorageProvider;
    private final BlockDataStorage blockDataStorage;

    @Autowired
    public FlatWorldGenerator(BlockDataStorageProvider blockDataStorageProvider) {
        super(Dimension.OVERWORLD, LevelType.FLAT);
        this.blockDataStorageProvider = blockDataStorageProvider;

        blockDataStorage = blockDataStorageProvider.getBlockDataStorage();
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                blockDataStorage.setBlock(x, 45, z, ImmutableBlock.of(VanillaBlockType.GRASS_BLOCK));
                for (int y = 44; y > 0; y--) {
                    blockDataStorage.setBlock(x, y, z, ImmutableBlock.of(VanillaBlockType.STONE));
                }

                blockDataStorage.setBlock(x, 0, z, ImmutableBlock.of(VanillaBlockType.BEDROCK));
                for (int y = 0; y < Chunk.HEIGHT; y++) {
                    blockDataStorage.setSkyLight(x, y, z, (byte) 15);
                }
            }
        }
    }

    @Override
    public Chunk generateChunk(int seed, ChunkCoords coords) {
        return new SimpleChunk(blockDataStorage.clone(), new EntityData(new HashSet<>()), coords);
    }

    @Override
    public RotatablePosition getFirstSpawnPosition(int seed) {
        return new RotatablePosition(new Position(0, 46, 0), new Rotation(0, 0));
    }

    @Override
    public String getName() {
        return "flatWorldGenerator";
    }
}
