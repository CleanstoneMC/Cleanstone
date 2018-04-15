package rocks.cleanstone.net.packet.protocol.minecraft.v1_12_2;

import rocks.cleanstone.net.packet.minecraft.receive.HandshakePacket;
import rocks.cleanstone.net.packet.protocol.ServerProtocolLayer;
import rocks.cleanstone.net.packet.protocol.minecraft.MinecraftClientProtocolLayer;
import rocks.cleanstone.net.packet.protocol.minecraft.v1_12_2.handshake.HandshakeCodec;

public class MinecraftProtocolLayer_v1_12_2 extends ServerProtocolLayer {

    public void init() {
        registerPacketCodec(new HandshakeCodec(), HandshakePacket.class);
    }

    @Override
    public MinecraftClientProtocolLayer getCorrespondingClientLayer() {
        return MinecraftClientProtocolLayer.MINECRAFT_V1_12_2;
    }
}
