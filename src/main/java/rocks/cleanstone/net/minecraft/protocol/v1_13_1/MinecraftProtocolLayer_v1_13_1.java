package rocks.cleanstone.net.minecraft.protocol.v1_13_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.protocol.MinecraftClientProtocolLayer;
import rocks.cleanstone.net.minecraft.protocol.v1_13.MinecraftProtocolLayer_v1_13;
import rocks.cleanstone.net.minecraft.protocol.v1_13.outbound.ChunkDataCodec;
import rocks.cleanstone.net.protocol.PacketCodec;

import java.util.List;

import static rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState.PLAY;

@Component("minecraftProtocolLayer_v1_13_1")
public class MinecraftProtocolLayer_v1_13_1 extends MinecraftProtocolLayer_v1_13 {

    @Autowired
    public MinecraftProtocolLayer_v1_13_1(List<? extends PacketCodec> packetCodecs) {
        super(packetCodecs);
        registerPacketCodec(ChunkDataCodec.class, PLAY, 0x22);
    }

    @Override
    public MinecraftClientProtocolLayer getCorrespondingClientLayer() {
        return MinecraftClientProtocolLayer.MINECRAFT_V1_13_1;
    }
}
