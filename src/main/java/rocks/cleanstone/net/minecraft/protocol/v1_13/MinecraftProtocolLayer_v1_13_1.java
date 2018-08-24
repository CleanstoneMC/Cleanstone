package rocks.cleanstone.net.minecraft.protocol.v1_13;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.protocol.MinecraftClientProtocolLayer;

@Component("minecraftProtocolLayer_v1_13_1")
public class MinecraftProtocolLayer_v1_13_1 extends MinecraftProtocolLayer_v1_13 {

    @Autowired
    public MinecraftProtocolLayer_v1_13_1(ProtocolBlockStateMapping blockStateMapping,
                                          ProtocolItemTypeMapping itemTypeMapping) {
        super(blockStateMapping, itemTypeMapping);
    }

    @Override
    public MinecraftClientProtocolLayer getCorrespondingClientLayer() {
        return MinecraftClientProtocolLayer.MINECRAFT_V1_13_1;
    }
}
