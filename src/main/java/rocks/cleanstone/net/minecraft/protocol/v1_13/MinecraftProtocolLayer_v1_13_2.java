package rocks.cleanstone.net.minecraft.protocol.v1_13;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.protocol.MinecraftClientProtocolLayer;
import rocks.cleanstone.net.protocol.PacketCodec;

import java.util.List;

@Component("minecraftProtocolLayer_v1_13_2")
public class MinecraftProtocolLayer_v1_13_2 extends MinecraftProtocolLayer_v1_13_1 {

    @Autowired
    public MinecraftProtocolLayer_v1_13_2(List<? extends PacketCodec> packetCodecs) {
        super(packetCodecs);
    }

    @Override
    public MinecraftClientProtocolLayer getCorrespondingClientLayer() {
        return MinecraftClientProtocolLayer.MINECRAFT_V1_13_2;
    }
}
