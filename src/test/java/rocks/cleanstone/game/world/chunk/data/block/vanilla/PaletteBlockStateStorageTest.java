package rocks.cleanstone.game.world.chunk.data.block.vanilla;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.block.state.SimpleBlockStateProvider;
import rocks.cleanstone.game.block.state.mapping.BlockStateMapping;
import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.game.material.SimpleMaterialRegistry;
import rocks.cleanstone.game.world.chunk.ArrayBlockDataTable;
import rocks.cleanstone.game.world.chunk.BlockDataTable;
import rocks.cleanstone.net.minecraft.protocol.v1_13.ProtocolBlockStateMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaletteBlockStateStorageTest {

    private Random random;
    private BlockStateMapping<Integer> blockStateMapping;
    private MaterialRegistry materialRegistry;
    private SimpleBlockStateProvider blockStateProvider;
    private PaletteBlockStateStorage storage;

    @BeforeEach
    void createStorageByTable() {
        blockStateProvider = new SimpleBlockStateProvider(new ConcurrentMapCacheManager());
        blockStateProvider.init();

        random = new Random(1);
        blockStateMapping = new ProtocolBlockStateMapping();
        materialRegistry = new SimpleMaterialRegistry();

        BlockDataTable blockDataTable = new ArrayBlockDataTable(true);
        for (int i = 0; i < 40; i++) {
            Block randomBlock = ImmutableBlock.of(
                    new ArrayList<>(materialRegistry.getBlockTypes())
                            .get(random.nextInt(materialRegistry.getBlockTypes().size())));
            blockDataTable.setBlock(random.nextInt(16), random.nextInt(256), random.nextInt(16), randomBlock);
        }
        storage = new PaletteBlockStateStorage(blockDataTable, 0, new AtomicBoolean(),
                new DirectPalette(blockStateMapping, 14), true);
    }

    @Test
    void testSerialization() {
        ByteBuf buf = Unpooled.buffer();
        storage.write(buf);
        PaletteBlockStateStorage deserialized;
        try {
            deserialized = new PaletteBlockStateStorage(buf, new DirectPalette(blockStateMapping, 14), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(storage, deserialized);
        buf.release();
    }

    @AfterEach
    void tearDown() {
        blockStateProvider.destroy();
    }
}