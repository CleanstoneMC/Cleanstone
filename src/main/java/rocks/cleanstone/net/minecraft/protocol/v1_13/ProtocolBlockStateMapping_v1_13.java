package rocks.cleanstone.net.minecraft.protocol.v1_13;

import org.springframework.stereotype.Component;

import rocks.cleanstone.game.block.state.property.PropertyDefinition;
import rocks.cleanstone.game.material.block.vanilla.VanillaBlockType;
import rocks.cleanstone.net.minecraft.protocol.v1_13_1.ProtocolBlockStateMapping_v1_13_1;

@Component("protocolBlockStateMapping_v1_13")
public class ProtocolBlockStateMapping_v1_13 extends ProtocolBlockStateMapping_v1_13_1 {

    public ProtocolBlockStateMapping_v1_13() {
        setDefaultProperties(VanillaBlockType.TNT, new PropertyDefinition[0]);
        shiftBiggerBaseIDs(getBaseID(VanillaBlockType.TNT) + 1, -1);
    }
}
