package rocks.cleanstone.game.world.chunk.data.block.vanilla;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.block.state.CachingBlockStateProvider;
import rocks.cleanstone.game.block.state.mapping.BlockStateMapping;
import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.game.material.SimpleMaterialRegistry;
import rocks.cleanstone.game.world.chunk.ArrayBlockDataTable;
import rocks.cleanstone.game.world.chunk.BlockDataTable;
import rocks.cleanstone.game.world.chunk.data.block.vanilla.section.PaletteBlockStateStorage;
import rocks.cleanstone.net.minecraft.protocol.v1_13_1.ProtocolBlockStateMapping_v1_13_1;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaletteBlockStateStorageTest {

    private Random random;
    private BlockStateMapping<Integer> blockStateMapping;
    private MaterialRegistry materialRegistry;
    private CachingBlockStateProvider blockStateProvider;
    private PaletteBlockStateStorage storage;

    @BeforeEach
    void createStorageByTable() {
        blockStateProvider = new CachingBlockStateProvider();
        random = new Random(1);
        blockStateMapping = new ProtocolBlockStateMapping_v1_13_1();
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
}