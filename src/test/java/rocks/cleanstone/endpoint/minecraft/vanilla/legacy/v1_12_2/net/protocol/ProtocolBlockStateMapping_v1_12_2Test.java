package rocks.cleanstone.endpoint.minecraft.vanilla.legacy.v1_12_2.net.protocol;

import org.junit.jupiter.api.Test;
import rocks.cleanstone.endpoint.minecraft.vanilla.block.VanillaBlockType;
import rocks.cleanstone.game.block.state.BlockState;

import static org.junit.jupiter.api.Assertions.*;

class ProtocolBlockStateMapping_v1_12_2Test {

    @Test
    void getID() {
        ProtocolBlockStateMapping_v1_12_2 blockStateMapping = new ProtocolBlockStateMapping_v1_12_2();

        assertEquals(0, blockStateMapping.getID(BlockState.of(VanillaBlockType.AIR)));
        assertEquals(112, blockStateMapping.getID(BlockState.of(VanillaBlockType.BEDROCK)));
        assertEquals(16, blockStateMapping.getID(BlockState.of(VanillaBlockType.STONE)));
    }

    @Test
    void getState() {
        ProtocolBlockStateMapping_v1_12_2 blockStateMapping = new ProtocolBlockStateMapping_v1_12_2();

        assertEquals(BlockState.of(VanillaBlockType.AIR), blockStateMapping.getState(0));
        assertEquals(BlockState.of(VanillaBlockType.BEDROCK), blockStateMapping.getState(112));
        assertEquals(BlockState.of(VanillaBlockType.STONE), blockStateMapping.getState(16));
    }
}