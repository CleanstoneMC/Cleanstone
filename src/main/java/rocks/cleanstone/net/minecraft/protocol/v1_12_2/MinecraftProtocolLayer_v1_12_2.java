package rocks.cleanstone.net.minecraft.protocol.v1_12_2;

import rocks.cleanstone.net.minecraft.packet.inbound.*;
import rocks.cleanstone.net.minecraft.packet.outbound.*;
import rocks.cleanstone.net.minecraft.protocol.MinecraftClientProtocolLayer;
import rocks.cleanstone.net.minecraft.protocol.MinecraftServerProtocolLayer;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.*;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound.*;

public class MinecraftProtocolLayer_v1_12_2 extends MinecraftServerProtocolLayer {

    public MinecraftProtocolLayer_v1_12_2() {
        registerPacketCodec(new HandshakeCodec(), HandshakePacket.class);
        registerPacketCodec(new LoginStartCodec(), LoginStartPacket.class);
        registerPacketCodec(new EncryptionRequestCodec(), EncryptionRequestPacket.class);
        registerPacketCodec(new RequestCodec(), RequestPacket.class);
        registerPacketCodec(new PingCodec(), PingPacket.class);

        registerPacketCodec(new DisconnectCodec(), DisconnectPacket.class);
        registerPacketCodec(new DisconnectLoginCodec(), DisconnectLoginPacket.class);
        registerPacketCodec(new EncryptionResponseCodec(), EncryptionResponsePacket.class);
        registerPacketCodec(new SetCompressionCodec(), SetCompressionPacket.class);
        registerPacketCodec(new LoginSuccessCodec(), LoginSuccessPacket.class);
        registerPacketCodec(new ResponseCodec(), ResponsePacket.class);
        registerPacketCodec(new PongCodec(), PongPacket.class);
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
