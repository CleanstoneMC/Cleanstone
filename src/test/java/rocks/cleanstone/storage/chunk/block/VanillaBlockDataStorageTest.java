package rocks.cleanstone.storage.chunk.block;

import io.netty.buffer.ByteBuf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.game.material.SimpleMaterialRegistry;
import rocks.cleanstone.game.world.chunk.ArrayBlockDataTable;
import rocks.cleanstone.game.world.chunk.BlockDataTable;
import rocks.cleanstone.net.minecraft.protocol.v1_14.ProtocolBlockStateMapping_v1_14;
import rocks.cleanstone.storage.chunk.block.vanilla.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

class VanillaBlockDataStorageTest {

    private Random random;
    private DirectPalette directPalette;
    private VanillaBlockDataStorage storage;
    private SimpleVanillaBlockDataStorageFactory simpleVanillaBlockDataStorageFactory;

    @BeforeEach
    void createStorageByTable() {
        simpleVanillaBlockDataStorageFactory = new SimpleVanillaBlockDataStorageFactory();

        random = new Random(1);
        directPalette = new DirectPalette(new ProtocolBlockStateMapping_v1_14(), 14);
        MaterialRegistry materialRegistry = new SimpleMaterialRegistry();

        BlockDataTable blockDataTable = new ArrayBlockDataTable(true);
        for (int i = 0; i < 20; i++) {
            Block randomBlock = ImmutableBlock.of(
                    new ArrayList<>(materialRegistry.getBlockTypes())
                            .get(random.nextInt(materialRegistry.getBlockTypes().size())));
            blockDataTable.setBlock(random.nextInt(16), random.nextInt(256), random.nextInt(16), randomBlock);
        }
        storage = simpleVanillaBlockDataStorageFactory.get(blockDataTable, directPalette, true, false, false);
    }

    @Disabled
    @Test
    void testSerializationAndTable() {
        VanillaBlockDataCodec codec = new SimpleVanillaBlockDataCodecFactory(simpleVanillaBlockDataStorageFactory)
                .get(directPalette, true, false, false, false, null);
        ByteBuf serialized = codec.encode(storage);
        VanillaBlockDataStorage deserialized;
        try {
            deserialized = codec.decode(serialized);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(storage.constructTable(), deserialized.constructTable());
        serialized.release();
    }
}
