package rocks.cleanstone.endpoint.minecraft.vanilla.modern.v1_13_1.net.protocol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocks.cleanstone.endpoint.minecraft.vanilla.modern.v1_13.net.protocol.MinecraftProtocolLayer_v1_13;
import rocks.cleanstone.endpoint.minecraft.vanilla.modern.v1_13_1.net.protocol.inbound.CreativeInventoryActionCodec;
import rocks.cleanstone.endpoint.minecraft.vanilla.modern.v1_13_1.net.protocol.outbound.BlockChangeCodec;
import rocks.cleanstone.endpoint.minecraft.vanilla.modern.v1_13_1.net.protocol.outbound.ChunkDataCodec;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.protocol.MinecraftClientProtocolLayer;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.protocol.PacketCodec;

import java.util.List;

import static rocks.cleanstone.endpoint.minecraft.vanilla.net.protocol.VanillaProtocolState.PLAY;

@Component("minecraftProtocolLayer_v1_13_1")
public class MinecraftProtocolLayer_v1_13_1 extends MinecraftProtocolLayer_v1_13 {

    @Autowired
    public MinecraftProtocolLayer_v1_13_1(List<? extends PacketCodec<? extends Packet>> packetCodecs) {
        super(packetCodecs);
        registerPacketCodec(CreativeInventoryActionCodec.class, PLAY, 0x24);
        registerPacketCodec(ChunkDataCodec.class, PLAY, 0x22);
        registerPacketCodec(BlockChangeCodec.class, PLAY, 0x0B);
    }

    @Override
    public MinecraftClientProtocolLayer getCorrespondingClientLayer() {
        return MinecraftClientProtocolLayer.MINECRAFT_V1_13_1;
    }
}
