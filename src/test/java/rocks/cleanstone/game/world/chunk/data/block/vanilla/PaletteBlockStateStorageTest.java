package rocks.cleanstone.game.world.chunk.data.block.vanilla;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.ImmutableBlock;
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

    private final Random random = new Random(1);
    private final BlockStateMapping<Integer> blockStateMapping = new ProtocolBlockStateMapping();
    private final MaterialRegistry materialRegistry = new SimpleMaterialRegistry();
    private PaletteBlockStateStorage storage;

    @BeforeEach
    void createStorageByTable() {
        BlockDataTable blockDataTable = new ArrayBlockDataTable(true);
        for (int i = 0; i < 40; i++) {
            Block randomBlock = ImmutableBlock.of(
                    new ArrayList<>(materialRegistry.getBlockTypes())
                            .get(random.nextInt(materialRegistry.getBlockTypes().size())));
            blockDataTable.setBlock(random.nextInt(16), random.nextInt(256), random.nextInt(16), randomBlock);
        }
        storage = new PaletteBlockStateStorage(blockDataTable, 0, new AtomicBoolean(), blockStateMapping);
    }

    @Test
    void testSerialization() {
        ByteBuf buf = Unpooled.buffer();
        storage.write(buf);
        PaletteBlockStateStorage deserialized;
        try {
            deserialized = new PaletteBlockStateStorage(buf, blockStateMapping);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(storage, deserialized);
        buf.release();
    }
}