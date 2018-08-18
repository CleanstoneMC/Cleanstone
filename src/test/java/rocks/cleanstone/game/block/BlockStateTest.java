package rocks.cleanstone.game.block;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.game.material.SimpleMaterialRegistry;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Random;

class BlockStateTest {

    private final Random random = new Random(1);
    private Collection<BlockState> states;
    // TODO use materialRegistry bean
    private MaterialRegistry materialRegistry = new SimpleMaterialRegistry();

    @BeforeEach
    void createStates() {
        states = new LinkedHashSet<>();
        materialRegistry.getBlockTypes().forEach(material -> {
//            states.add(BlockState.of(material, (byte) random.nextInt(16)));
        });
    }

    @Test
    void testRawStates() {
        states.forEach(state -> {
//            assertEquals(BlockState.of(state.getRaw(), materialRegistry), state);
        });
    }
}
