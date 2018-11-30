package rocks.cleanstone.game.block.state.mapping;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.game.material.SimpleMaterialRegistry;
import rocks.cleanstone.net.minecraft.protocol.v1_13_1.ProtocolBlockStateMapping_v1_13_1;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModernBlockStateMappingTest {

    private BlockStateMapping<Integer> blockStateMapping;
    private MaterialRegistry materialRegistry;

    @BeforeEach
    void setUp() {
        blockStateMapping = new ProtocolBlockStateMapping_v1_13_1();
        materialRegistry = new SimpleMaterialRegistry();
    }

    @Test
    void serializationShouldBeOneToOne() {
        materialRegistry.getBlockTypes().forEach(blockType -> {
            BlockState state = BlockState.of(blockType);
            BlockState deserialized = blockStateMapping.getState(blockStateMapping.getID(state));
            assertEquals(state, deserialized);
        });
    }
}