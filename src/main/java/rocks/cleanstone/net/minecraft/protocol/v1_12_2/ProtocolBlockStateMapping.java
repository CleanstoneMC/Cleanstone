package rocks.cleanstone.net.minecraft.protocol.v1_12_2;

import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.block.state.mapping.LegacyBlockStateMapping;
import rocks.cleanstone.game.material.block.vanilla.VanillaBlockType;

public class ProtocolBlockStateMapping extends LegacyBlockStateMapping {
    public ProtocolBlockStateMapping() {
        super(BlockState.of(VanillaBlockType.STONE));

        setID(VanillaBlockType.AIR, 0);
        setID(VanillaBlockType.STONE, 1);
        // ...
    }
}
