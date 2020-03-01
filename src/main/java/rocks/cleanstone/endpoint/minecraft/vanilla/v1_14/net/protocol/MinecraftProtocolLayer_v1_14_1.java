package rocks.cleanstone.endpoint.minecraft.vanilla.v1_14.net.protocol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import rocks.cleanstone.endpoint.minecraft.vanilla.net.protocol.MinecraftClientProtocolLayer;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.protocol.PacketCodec;

@Component("minecraftProtocolLayer_v1_14_1")
public class MinecraftProtocolLayer_v1_14_1 extends MinecraftProtocolLayer_v1_14 {

    @Autowired
    public MinecraftProtocolLayer_v1_14_1(List<? extends PacketCodec<? extends Packet>> packetCodecs) {
        super(packetCodecs);
    }

    @Override
    public MinecraftClientProtocolLayer getCorrespondingClientLayer() {
        return MinecraftClientProtocolLayer.MINECRAFT_V1_14_1;
    }
}
