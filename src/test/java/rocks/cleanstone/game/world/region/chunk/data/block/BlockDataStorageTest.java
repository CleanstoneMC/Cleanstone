package rocks.cleanstone.game.world.region.chunk.data.block;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Random;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.material.VanillaMaterial;
import rocks.cleanstone.game.world.region.chunk.ArrayBlockDataTable;
import rocks.cleanstone.game.world.region.chunk.BlockDataTable;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlockDataStorageTest {

    private final Random random = new Random(1);
    private BlockDataStorage storage;

    @BeforeEach
    void createStorageByTable() {
        BlockDataTable blockDataTable = new ArrayBlockDataTable(false);
        for (int i = 0; i < 20; i++) {
            Block randomBlock = ImmutableBlock.of(
                    VanillaMaterial.values()[random.nextInt(VanillaMaterial.values().length)]);
            blockDataTable.setBlock(random.nextInt(16), random.nextInt(256), random.nextInt(16), randomBlock);
        }
        storage = new BlockDataStorage(blockDataTable);
    }

    @Test
    void testSerializationAndTable() {
        ByteBuf buf = Unpooled.buffer();
        storage.write(buf);
        BlockDataStorage deserialized;
        try {
            deserialized = new BlockDataStorage(buf, false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(storage.constructTable(), deserialized.constructTable());
        buf.release();
    }
}