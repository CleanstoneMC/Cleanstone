package rocks.cleanstone.net.minecraft.protocol.v1_13;

import org.springframework.beans.factory.annotation.Autowired;

import rocks.cleanstone.net.minecraft.protocol.MinecraftClientProtocolLayer;

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
