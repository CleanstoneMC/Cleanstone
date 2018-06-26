package rocks.cleanstone.game.block;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Random;

import rocks.cleanstone.game.material.VanillaMaterial;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlockStateTest {

    private final Random random = new Random(1);
    private Collection<BlockState> states;

    @BeforeEach
    void createStates() {
        states = new LinkedHashSet<>();
        Arrays.stream(VanillaMaterial.values()).forEachOrdered(material -> {
            states.add(BlockState.of(material, (byte) random.nextInt(16)));
        });
    }

    @Test
    void testRawStates() {
        states.forEach(state -> {
            assertEquals(BlockState.of(state.getRaw()), state);
        });
    }
}
