package rocks.cleanstone.net.packet.protocol.minecraft.v1_12_2;

import rocks.cleanstone.net.packet.minecraft.inbound.HandshakePacket;
import rocks.cleanstone.net.packet.protocol.minecraft.MinecraftClientProtocolLayer;
import rocks.cleanstone.net.packet.protocol.minecraft.MinecraftServerProtocolLayer;
import rocks.cleanstone.net.packet.protocol.minecraft.v1_12_2.inbound.HandshakeCodec;

public class MinecraftProtocolLayer_v1_12_2 extends MinecraftServerProtocolLayer {

    public void init() {
        HandshakeCodec handshakeCodec = new HandshakeCodec();
        registerPacketCodec(handshakeCodec, HandshakePacket.class);
    }

    @Override
    public MinecraftClientProtocolLayer getCorrespondingClientLayer() {
        return MinecraftClientProtocolLayer.MINECRAFT_V1_12_2;
    }

    @Override
    public int getOrderedID() {
        return 0;
    }
}
