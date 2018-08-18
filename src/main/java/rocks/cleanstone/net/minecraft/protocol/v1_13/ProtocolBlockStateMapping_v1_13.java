package rocks.cleanstone.net.minecraft.protocol.v1_13;

import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.block.state.mapping.ModernBlockStateMapping;
import rocks.cleanstone.game.material.block.vanilla.VanillaBlockType;

public class ProtocolBlockStateMapping_v1_13 extends ModernBlockStateMapping {

    public ProtocolBlockStateMapping_v1_13() {
        super(BlockState.of(VanillaBlockType.STONE));
        setBaseID(VanillaBlockType.AIR, 0);
        setBaseID(VanillaBlockType.STONE, 1);
        setBaseID(VanillaBlockType.GRANITE, 2);
        // ...
    }
}
