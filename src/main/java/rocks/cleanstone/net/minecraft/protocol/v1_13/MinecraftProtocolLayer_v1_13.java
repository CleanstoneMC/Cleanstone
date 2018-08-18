package rocks.cleanstone.net.minecraft.protocol.v1_13;

import org.springframework.beans.factory.annotation.Autowired;

import rocks.cleanstone.net.minecraft.protocol.MinecraftClientProtocolLayer;
import rocks.cleanstone.net.minecraft.protocol.MinecraftServerProtocolLayer;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.ClientSettingsCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.CreativeInventoryActionCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.EncryptionResponseCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.HandshakeCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.HeldItemChangeCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.InChatMessageCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.InKeepAliveCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.InPlayerAbilitiesCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.InTabCompleteCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.LoginStartCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.PingCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.PlayerBlockPlacementCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.PlayerDiggingCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.PlayerLookCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.PlayerPositionAndLookCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.PlayerPositionCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.RequestCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.UseItemCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound.*;
import rocks.cleanstone.net.packet.inbound.ClientSettingsPacket;
import rocks.cleanstone.net.packet.inbound.CreativeInventoryActionPacket;
import rocks.cleanstone.net.packet.inbound.EncryptionResponsePacket;
import rocks.cleanstone.net.packet.inbound.HandshakePacket;
import rocks.cleanstone.net.packet.inbound.HeldItemChangePacket;
import rocks.cleanstone.net.packet.inbound.InChatMessagePacket;
import rocks.cleanstone.net.packet.inbound.InKeepAlivePacket;
import rocks.cleanstone.net.packet.inbound.InPlayerAbilitiesPacket;
import rocks.cleanstone.net.packet.inbound.InPlayerPositionAndLookPacket;
import rocks.cleanstone.net.packet.inbound.InTabCompletePacket;
import rocks.cleanstone.net.packet.inbound.LoginStartPacket;
import rocks.cleanstone.net.packet.inbound.PingPacket;
import rocks.cleanstone.net.packet.inbound.PlayerBlockPlacementPacket;
import rocks.cleanstone.net.packet.inbound.PlayerDiggingPacket;
import rocks.cleanstone.net.packet.inbound.PlayerLookPacket;
import rocks.cleanstone.net.packet.inbound.PlayerPositionPacket;
import rocks.cleanstone.net.packet.inbound.RequestPacket;
import rocks.cleanstone.net.packet.inbound.UseItemPacket;
import rocks.cleanstone.net.packet.outbound.*;

import static rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState.*;

public class MinecraftProtocolLayer_v1_13 extends MinecraftServerProtocolLayer {
    @Autowired
    public MinecraftProtocolLayer_v1_13(ProtocolBlockStateMapping_v1_13 blockStateMapping) {
        // inbound
        registerPacketCodec(new HandshakeCodec(), HandshakePacket.class, HANDSHAKE, 0x00);
        registerPacketCodec(new LoginStartCodec(), LoginStartPacket.class, LOGIN, 0x00);
        registerPacketCodec(new EncryptionRequestCodec(), EncryptionRequestPacket.class, LOGIN, 0x01);
        registerPacketCodec(new RequestCodec(), RequestPacket.class, STATUS, 0x00);
        registerPacketCodec(new PingCodec(), PingPacket.class, STATUS, 0x01);
        registerPacketCodec(new ClientSettingsCodec(), ClientSettingsPacket.class, PLAY, 0x04);
        registerPacketCodec(new InKeepAliveCodec(), InKeepAlivePacket.class, PLAY, 0x0E);
        registerPacketCodec(new InChatMessageCodec(), InChatMessagePacket.class, PLAY, 0x02);
        registerPacketCodec(new InTabCompleteCodec(), InTabCompletePacket.class, PLAY, 0x05);
        registerPacketCodec(new PlayerPositionCodec(), PlayerPositionPacket.class, PLAY, 0x10);
        registerPacketCodec(new PlayerLookCodec(), PlayerLookPacket.class, PLAY, 0x12);
        registerPacketCodec(new PlayerPositionAndLookCodec(), InPlayerPositionAndLookPacket.class, PLAY, 0x11);
        registerPacketCodec(new UseItemCodec(), UseItemPacket.class, PLAY, 0x2A);
        registerPacketCodec(new PlayerBlockPlacementCodec(), PlayerBlockPlacementPacket.class, PLAY, 0x29);
        registerPacketCodec(new PlayerDiggingCodec(), PlayerDiggingPacket.class, PLAY, 0x18);
        registerPacketCodec(new CreativeInventoryActionCodec(blockStateMapping), CreativeInventoryActionPacket.class, PLAY, 0x24);
        registerPacketCodec(new HeldItemChangeCodec(), HeldItemChangePacket.class, PLAY, 0x21);
        registerPacketCodec(new InPlayerAbilitiesCodec(), InPlayerAbilitiesPacket.class, PLAY, 0x17);

        // outbound
        registerPacketCodec(new DisconnectCodec(), DisconnectPacket.class, PLAY, 0x1B);
        registerPacketCodec(new DisconnectLoginCodec(), DisconnectLoginPacket.class, LOGIN, 0x00);
        registerPacketCodec(new SpawnMobCodec(), SpawnMobPacket.class, PLAY, 0x03);
        registerPacketCodec(new EncryptionResponseCodec(), EncryptionResponsePacket.class, LOGIN, 0x01);
        registerPacketCodec(new SetCompressionCodec(), SetCompressionPacket.class, LOGIN, 0x03);
        registerPacketCodec(new LoginSuccessCodec(), LoginSuccessPacket.class, LOGIN, 0x02);
        registerPacketCodec(new ResponseCodec(), ResponsePacket.class, STATUS, 0x00);
        registerPacketCodec(new PongCodec(), PongPacket.class, STATUS, 0x01);
        registerPacketCodec(new JoinGameCodec(), JoinGamePacket.class, PLAY, 0x25);
        registerPacketCodec(new SpawnPositionCodec(), SpawnPositionPacket.class, PLAY, 0x49);
        registerPacketCodec(new OutPlayerAbilitiesCodec(), OutPlayerAbilitiesPacket.class, PLAY, 0x2E);
        registerPacketCodec(new OutPlayerPositionAndLookCodec(), OutPlayerPositionAndLookPacket.class, PLAY, 0x32);
        registerPacketCodec(new ChunkDataCodec(blockStateMapping), ChunkDataPacket.class, PLAY, 0x22);
        registerPacketCodec(new OutKeepAliveCodec(), OutKeepAlivePacket.class, PLAY, 0x21);
        registerPacketCodec(new OutTabCompleteCodec(), OutTabCompletePacket.class, PLAY, 0x10);
        registerPacketCodec(new OutChatMessageCodec(), OutChatMessagePacket.class, PLAY, 0x0E);
        registerPacketCodec(new WindowItemsCodec(), WindowItemsPacket.class, PLAY, 0x15);
        registerPacketCodec(new PlayerListItemCodec(), PlayerListItemPacket.class, PLAY, 0x30);
        registerPacketCodec(new EntityLookCodec(), EntityLookPacket.class, PLAY, 0x2A);
        registerPacketCodec(new EntityRelativeMoveCodec(), EntityRelativeMovePacket.class, PLAY, 0x28);
        registerPacketCodec(new EntityLookAndRelativeMoveCodec(), EntityLookAndRelativeMovePacket.class, PLAY, 0x29);
        registerPacketCodec(new SpawnPlayerCodec(), SpawnPlayerPacket.class, PLAY, 0x05);
        registerPacketCodec(new TimeUpdateCodec(), TimeUpdatePacket.class, PLAY, 0x47);
        registerPacketCodec(new UnloadChunkCodec(), UnloadChunkPacket.class, PLAY, 0x1F);
        registerPacketCodec(new BlockChangeCodec(), BlockChangePacket.class, PLAY, 0x0B);
        registerPacketCodec(new EntityTeleportCodec(), EntityTeleportPacket.class, PLAY, 0x50);
        registerPacketCodec(new DestroyEntitiesCodec(), DestroyEntitiesPacket.class, PLAY, 0x35);
        registerPacketCodec(new ChangeGameStateCodec(), ChangeGameStatePacket.class, PLAY, 0x20);
        registerPacketCodec(new EntityHeadLookCodec(), EntityHeadLookPacket.class, PLAY, 0x39);
    }

    @Override
    public MinecraftClientProtocolLayer getCorrespondingClientLayer() {
        return MinecraftClientProtocolLayer.MINECRAFT_V1_13;
    }

    @Override
    public int getOrderedID() {
        return 1;
    }
}
