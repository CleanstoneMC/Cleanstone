package rocks.cleanstone.game.block.state.mapping;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rocks.cleanstone.endpoint.minecraft.java.v1_14.protocol.ProtocolBlockStateMapping_v1_14;
import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.game.material.SimpleMaterialRegistry;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModernBlockStateMappingTest {

    private BlockStateMapping<Integer> blockStateMapping;
    private MaterialRegistry materialRegistry;

    @BeforeEach
    void setUp() {
        blockStateMapping = new ProtocolBlockStateMapping_v1_14();
        materialRegistry = new SimpleMaterialRegistry();
    }

    /**
     * Checks if deserializing a serialized block state returns the same block state. This only tests the
     * default block states.
     *
     * TODO: Currently, block states which have int properties that have different value ranges for different
     *  block states are not serialized properly (distance, level, range, ...)
     */
    @Test
    void serializationShouldBeOneToOne() {
        materialRegistry.getBlockTypes().forEach(blockType -> {
            BlockState state = BlockState.of(blockType);
            BlockState deserialized = blockStateMapping.getState(blockStateMapping.getID(state));
            assertEquals(state, deserialized);
        });
    }
}