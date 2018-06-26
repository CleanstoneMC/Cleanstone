package rocks.cleanstone.game.world.region.chunk.data.block.vanilla;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.material.VanillaMaterial;
import rocks.cleanstone.game.world.region.chunk.ArrayBlockDataTable;
import rocks.cleanstone.game.world.region.chunk.BlockDataTable;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaletteBlockStateStorageTest {

    private final Random random = new Random(1);
    private PaletteBlockStateStorage storage;

    @BeforeEach
    void createStorageByTable() {
        BlockDataTable blockDataTable = new ArrayBlockDataTable(false);
        for (int i = 0; i < 40; i++) {
            Block randomBlock = ImmutableBlock.of(
                    VanillaMaterial.values()[random.nextInt(VanillaMaterial.values().length)]);
            blockDataTable.setBlock(random.nextInt(16), random.nextInt(256), random.nextInt(16), randomBlock);
        }
        storage = new PaletteBlockStateStorage(blockDataTable, 0, new AtomicBoolean());
    }

    @Test
    void testSerialization() {
        ByteBuf buf = Unpooled.buffer();
        storage.write(buf);
        PaletteBlockStateStorage deserialized;
        try {
            deserialized = new PaletteBlockStateStorage(buf);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(storage, deserialized);
        buf.release();
    }

    @Test
    void testSet() {

    }
}