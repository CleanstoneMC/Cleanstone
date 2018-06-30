package rocks.cleanstone.game.world.chunk.data.block;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Random;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.material.VanillaMaterial;
import rocks.cleanstone.game.world.chunk.ArrayBlockDataTable;
import rocks.cleanstone.game.world.chunk.BlockDataTable;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlockDataStorageTest {

    private final Random random = new Random(1);
    private BlockDataStorage storage;

    @BeforeEach
    void createStorageByTable() {
        BlockDataTable blockDataTable = new ArrayBlockDataTable(true);
        for (int i = 0; i < 20; i++) {
            Block randomBlock = ImmutableBlock.of(
                    VanillaMaterial.values()[random.nextInt(VanillaMaterial.values().length)]);
            blockDataTable.setBlock(random.nextInt(16), random.nextInt(256), random.nextInt(16), randomBlock);
        }
        storage = new BlockDataStorage(blockDataTable);
    }

    @Test
    void testSerializationAndTable() {
        BlockDataCodec codec = new BlockDataCodec();
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