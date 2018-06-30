package rocks.cleanstone.net.minecraft.protocol.v1_12_2;

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

public class MinecraftProtocolLayer_v1_12_2 extends MinecraftServerProtocolLayer {

    public MinecraftProtocolLayer_v1_12_2() {
        // inbound
        registerPacketCodec(new HandshakeCodec(), HandshakePacket.class);
        registerPacketCodec(new LoginStartCodec(), LoginStartPacket.class);
        registerPacketCodec(new EncryptionRequestCodec(), EncryptionRequestPacket.class);
        registerPacketCodec(new RequestCodec(), RequestPacket.class);
        registerPacketCodec(new PingCodec(), PingPacket.class);
        registerPacketCodec(new ClientSettingsCodec(), ClientSettingsPacket.class);
        registerPacketCodec(new InKeepAliveCodec(), InKeepAlivePacket.class);
        registerPacketCodec(new InChatMessageCodec(), InChatMessagePacket.class);
        registerPacketCodec(new InTabCompleteCodec(), InTabCompletePacket.class);
        registerPacketCodec(new PlayerPositionCodec(), PlayerPositionPacket.class);
        registerPacketCodec(new PlayerLookCodec(), PlayerLookPacket.class);
        registerPacketCodec(new PlayerPositionAndLookCodec(), InPlayerPositionAndLookPacket.class);
        registerPacketCodec(new UseItemCodec(), UseItemPacket.class);
        registerPacketCodec(new PlayerBlockPlacementCodec(), PlayerBlockPlacementPacket.class);
        registerPacketCodec(new PlayerDiggingCodec(), PlayerDiggingPacket.class);
        registerPacketCodec(new CreativeInventoryActionCodec(), CreativeInventoryActionPacket.class);
        registerPacketCodec(new HeldItemChangeCodec(), HeldItemChangePacket.class);
        registerPacketCodec(new InPlayerAbilitiesCodec(), InPlayerAbilitiesPacket.class);

        // outbound
        registerPacketCodec(new DisconnectCodec(), DisconnectPacket.class);
        registerPacketCodec(new DisconnectLoginCodec(), DisconnectLoginPacket.class);
        registerPacketCodec(new EncryptionResponseCodec(), EncryptionResponsePacket.class);
        registerPacketCodec(new SetCompressionCodec(), SetCompressionPacket.class);
        registerPacketCodec(new LoginSuccessCodec(), LoginSuccessPacket.class);
        registerPacketCodec(new ResponseCodec(), ResponsePacket.class);
        registerPacketCodec(new PongCodec(), PongPacket.class);
        registerPacketCodec(new JoinGameCodec(), JoinGamePacket.class);
        registerPacketCodec(new SpawnPositionCodec(), SpawnPositionPacket.class);
        registerPacketCodec(new OutPlayerAbilitiesCodec(), OutPlayerAbilitiesPacket.class);
        registerPacketCodec(new OutPlayerPositionAndLookCodec(), OutPlayerPositionAndLookPacket.class);
        registerPacketCodec(new ChunkDataCodec(), ChunkDataPacket.class);
        registerPacketCodec(new OutKeepAliveCodec(), OutKeepAlivePacket.class);
        registerPacketCodec(new OutTabCompleteCodec(), OutTabCompletePacket.class);
        registerPacketCodec(new OutChatMessageCodec(), OutChatMessagePacket.class);
        registerPacketCodec(new WindowItemsCodec(), WindowItemsPacket.class);
        registerPacketCodec(new PlayerListItemCodec(), PlayerListItemPacket.class);
        registerPacketCodec(new EntityLookCodec(), EntityLookPacket.class);
        registerPacketCodec(new EntityRelativeMoveCodec(), EntityRelativeMovePacket.class);
        registerPacketCodec(new EntityLookAndRelativeMoveCodec(), EntityLookAndRelativeMovePacket.class);
        registerPacketCodec(new SpawnPlayerCodec(), SpawnPlayerPacket.class);
        registerPacketCodec(new TimeUpdateCodec(), TimeUpdatePacket.class);
        registerPacketCodec(new UnloadChunkCodec(), UnloadChunkPacket.class);
        registerPacketCodec(new BlockChangeCodec(), BlockChangePacket.class);
        registerPacketCodec(new EntityTeleportCodec(), EntityTeleportPacket.class);
        registerPacketCodec(new DestroyEntitiesCodec(), DestroyEntitiesPacket.class);
        registerPacketCodec(new ChangeGameStateCodec(), ChangeGameStatePacket.class);
        registerPacketCodec(new EntityHeadLookCodec(), EntityHeadLookPacket.class);
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
