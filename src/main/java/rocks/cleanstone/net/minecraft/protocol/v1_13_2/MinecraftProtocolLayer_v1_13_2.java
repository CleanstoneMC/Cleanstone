package rocks.cleanstone.net.minecraft.protocol.v1_13_2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import rocks.cleanstone.net.minecraft.protocol.MinecraftClientProtocolLayer;
import rocks.cleanstone.net.minecraft.protocol.v1_13_1.MinecraftProtocolLayer_v1_13_1;
import rocks.cleanstone.net.minecraft.protocol.v1_13_2.inbound.CreativeInventoryActionCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_13_2.outbound.BlockChangeCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_13_2.outbound.ChunkDataCodec;
import rocks.cleanstone.net.protocol.PacketCodec;

import static rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState.PLAY;

@Component("minecraftProtocolLayer_v1_13_2")
public class MinecraftProtocolLayer_v1_13_2 extends MinecraftProtocolLayer_v1_13_1 {

    @Autowired
    public MinecraftProtocolLayer_v1_13_2(List<? extends PacketCodec> packetCodecs) {
        super(packetCodecs);
        registerPacketCodec(CreativeInventoryActionCodec.class, PLAY, 0x24);
        registerPacketCodec(ChunkDataCodec.class, PLAY, 0x22);
        registerPacketCodec(BlockChangeCodec.class, PLAY, 0x0B);
    }

    @Override
    public MinecraftClientProtocolLayer getCorrespondingClientLayer() {
        return MinecraftClientProtocolLayer.MINECRAFT_V1_13_2;
    }
}
