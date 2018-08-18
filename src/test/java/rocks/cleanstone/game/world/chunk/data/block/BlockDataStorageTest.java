package rocks.cleanstone.game.world.chunk.data.block;

import io.netty.buffer.ByteBuf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.block.state.mapping.BlockStateMapping;
import rocks.cleanstone.game.block.state.mapping.ModernBlockStateMapping;
import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.game.material.SimpleMaterialRegistry;
import rocks.cleanstone.game.material.block.vanilla.VanillaBlockType;
import rocks.cleanstone.game.world.chunk.ArrayBlockDataTable;
import rocks.cleanstone.game.world.chunk.BlockDataTable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlockDataStorageTest {

    private final Random random = new Random(1);
    private BlockDataStorage storage;
    // TODO use materialRegistry bean
    private MaterialRegistry materialRegistry = new SimpleMaterialRegistry();
    private final BlockStateMapping<Integer> blockStateMapping = new ModernBlockStateMapping(BlockState.of(VanillaBlockType.STONE));

    @BeforeEach
    void createStorageByTable() {
        BlockDataTable blockDataTable = new ArrayBlockDataTable(true);
        for (int i = 0; i < 20; i++) {
            Block randomBlock = ImmutableBlock.of(
                    new ArrayList<>(materialRegistry.getBlockTypes())
                            .get(random.nextInt(materialRegistry.getBlockTypes().size())));
            blockDataTable.setBlock(random.nextInt(16), random.nextInt(256), random.nextInt(16), randomBlock);
        }
        storage = new BlockDataStorage(blockDataTable, blockStateMapping);
    }

    @Test
    void testSerializationAndTable() {
        BlockDataCodec codec = new BlockDataCodec(blockStateMapping);
        ByteBuf serialized = codec.serialize(storage);
        BlockDataStorage deserialized;
        try {
            deserialized = codec.deserialize(serialized);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(storage.constructTable(), deserialized.constructTable());
        serialized.release();
    }
}