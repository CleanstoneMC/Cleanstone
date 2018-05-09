package rocks.cleanstone.net.minecraft.protocol.v1_12_2;

import rocks.cleanstone.net.minecraft.packet.inbound.ClientSettingsPacket;
import rocks.cleanstone.net.minecraft.packet.inbound.EncryptionResponsePacket;
import rocks.cleanstone.net.minecraft.packet.inbound.HandshakePacket;
import rocks.cleanstone.net.minecraft.packet.inbound.LoginStartPacket;
import rocks.cleanstone.net.minecraft.packet.inbound.PingPacket;
import rocks.cleanstone.net.minecraft.packet.inbound.RequestPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.ChunkDataPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.DisconnectLoginPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.DisconnectPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.EncryptionRequestPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.JoinGamePacket;
import rocks.cleanstone.net.minecraft.packet.outbound.LoginSuccessPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.PongPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.ResponsePacket;
import rocks.cleanstone.net.minecraft.packet.outbound.SetCompressionPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.SpawnPositionPacket;
import rocks.cleanstone.net.minecraft.protocol.MinecraftClientProtocolLayer;
import rocks.cleanstone.net.minecraft.protocol.MinecraftServerProtocolLayer;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.ClientSettingsCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.EncryptionResponseCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.HandshakeCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.LoginStartCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.PingCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.RequestCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound.ChunkDataCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound.DisconnectCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound.DisconnectLoginCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound.EncryptionRequestCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound.JoinGameCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound.LoginSuccessCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound.PlayerAbilitiesCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound.PlayerPositionAndLookCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound.PongCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound.ResponseCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound.SetCompressionCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound.SpawnPositionCodec;

public class MinecraftProtocolLayer_v1_12_2 extends MinecraftServerProtocolLayer {

    public MinecraftProtocolLayer_v1_12_2() {
        registerPacketCodec(new HandshakeCodec(), HandshakePacket.class);
        registerPacketCodec(new LoginStartCodec(), LoginStartPacket.class);
        registerPacketCodec(new EncryptionRequestCodec(), EncryptionRequestPacket.class);
        registerPacketCodec(new RequestCodec(), RequestPacket.class);
        registerPacketCodec(new PingCodec(), PingPacket.class);
        registerPacketCodec(new ClientSettingsCodec(), ClientSettingsPacket.class);

        registerPacketCodec(new DisconnectCodec(), DisconnectPacket.class);
        registerPacketCodec(new DisconnectLoginCodec(), DisconnectLoginPacket.class);
        registerPacketCodec(new EncryptionResponseCodec(), EncryptionResponsePacket.class);
        registerPacketCodec(new SetCompressionCodec(), SetCompressionPacket.class);
        registerPacketCodec(new LoginSuccessCodec(), LoginSuccessPacket.class);
        registerPacketCodec(new ResponseCodec(), ResponsePacket.class);
        registerPacketCodec(new PongCodec(), PongPacket.class);
        registerPacketCodec(new JoinGameCodec(), JoinGamePacket.class);
        registerPacketCodec(new SpawnPositionCodec(), SpawnPositionPacket.class);
        registerPacketCodec(new PlayerAbilitiesCodec(), rocks.cleanstone.net.minecraft.packet.outbound.PlayerAbilitiesPacket.class);
        registerPacketCodec(new PlayerPositionAndLookCodec(), rocks.cleanstone.net.minecraft.packet.outbound.PlayerPositionAndLookPacket.class);
        registerPacketCodec(new ChunkDataCodec(), ChunkDataPacket.class);
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
