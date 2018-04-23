package rocks.cleanstone.net.packet.protocol.minecraft.v1_12_2;

import rocks.cleanstone.net.packet.minecraft.inbound.EncryptionResponsePacket;
import rocks.cleanstone.net.packet.minecraft.inbound.HandshakePacket;
import rocks.cleanstone.net.packet.minecraft.inbound.LoginStartPacket;
import rocks.cleanstone.net.packet.minecraft.outbound.EncryptionRequestPacket;
import rocks.cleanstone.net.packet.minecraft.outbound.LoginSuccessPacket;
import rocks.cleanstone.net.packet.minecraft.outbound.SetCompressionPacket;
import rocks.cleanstone.net.packet.protocol.minecraft.MinecraftClientProtocolLayer;
import rocks.cleanstone.net.packet.protocol.minecraft.MinecraftServerProtocolLayer;
import rocks.cleanstone.net.packet.protocol.minecraft.v1_12_2.inbound.EncryptionResponseCodec;
import rocks.cleanstone.net.packet.protocol.minecraft.v1_12_2.inbound.HandshakeCodec;
import rocks.cleanstone.net.packet.protocol.minecraft.v1_12_2.inbound.LoginStartCodec;
import rocks.cleanstone.net.packet.protocol.minecraft.v1_12_2.outbound.EncryptionRequestCodec;
import rocks.cleanstone.net.packet.protocol.minecraft.v1_12_2.outbound.LoginSuccessCodec;
import rocks.cleanstone.net.packet.protocol.minecraft.v1_12_2.outbound.SetCompressionCodec;

public class MinecraftProtocolLayer_v1_12_2 extends MinecraftServerProtocolLayer {

    public void init() {
        registerPacketCodec(new HandshakeCodec(), HandshakePacket.class);
        registerPacketCodec(new LoginStartCodec(), LoginStartPacket.class);
        registerPacketCodec(new EncryptionRequestCodec(), EncryptionRequestPacket.class);

        registerPacketCodec(new EncryptionResponseCodec(), EncryptionResponsePacket.class);
        registerPacketCodec(new SetCompressionCodec(), SetCompressionPacket.class);
        registerPacketCodec(new LoginSuccessCodec(), LoginSuccessPacket.class);
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
