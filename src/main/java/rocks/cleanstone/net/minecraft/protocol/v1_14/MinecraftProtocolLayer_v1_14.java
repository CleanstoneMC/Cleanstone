package rocks.cleanstone.net.minecraft.protocol.v1_14;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import rocks.cleanstone.net.minecraft.protocol.AutowiredServerProtocolLayer;
import rocks.cleanstone.net.minecraft.protocol.MinecraftClientProtocolLayer;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.ClientSettingsCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.EncryptionResponseCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.HandshakeCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.HeldItemChangeCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.InAnimationCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.InChatMessageCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.InKeepAliveCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.InPlayerAbilitiesCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.InPluginMessageCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.InTabCompleteCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.LoginStartCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.PingCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.PlayerCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.PlayerLookCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.PlayerPositionAndLookCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.PlayerPositionCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.RequestCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.UseItemCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound.*;
import rocks.cleanstone.net.minecraft.protocol.v1_14.inbound.CreativeInventoryActionCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_14.inbound.PlayerBlockPlacementCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_14.inbound.PlayerDiggingCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_14.outbound.BlockChangeCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_14.outbound.ChunkDataCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_14.outbound.JoinGameCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_14.outbound.UpdateViewPositionCodec;
import rocks.cleanstone.net.protocol.PacketCodec;

import static rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState.*;

@Component("minecraftProtocolLayer_v1_14")
public class MinecraftProtocolLayer_v1_14 extends AutowiredServerProtocolLayer {

    @Autowired
    public MinecraftProtocolLayer_v1_14(List<? extends PacketCodec> packetCodecs) {
        super(packetCodecs);
        // inbound
        registerPacketCodec(HandshakeCodec.class, HANDSHAKE, 0x00);
        registerPacketCodec(LoginStartCodec.class, LOGIN, 0x00);
        registerPacketCodec(EncryptionRequestCodec.class, LOGIN, 0x01);
        registerPacketCodec(RequestCodec.class, STATUS, 0x00);
        registerPacketCodec(PingCodec.class, STATUS, 0x01);
        registerPacketCodec(ClientSettingsCodec.class, PLAY, 0x05);
        registerPacketCodec(InKeepAliveCodec.class, PLAY, 0x0F);
        registerPacketCodec(InChatMessageCodec.class, PLAY, 0x03);
        registerPacketCodec(InTabCompleteCodec.class, PLAY, 0x06);
        registerPacketCodec(PlayerPositionCodec.class, PLAY, 0x11);
        registerPacketCodec(PlayerLookCodec.class, PLAY, 0x13);
        registerPacketCodec(PlayerPositionAndLookCodec.class, PLAY, 0x12);
        registerPacketCodec(UseItemCodec.class, PLAY, 0x2D);
        registerPacketCodec(PlayerBlockPlacementCodec.class, PLAY, 0x2C);
        registerPacketCodec(PlayerDiggingCodec.class, PLAY, 0x1A);
        registerPacketCodec(CreativeInventoryActionCodec.class, PLAY, 0x26);
        registerPacketCodec(HeldItemChangeCodec.class, PLAY, 0x23);
        registerPacketCodec(InPlayerAbilitiesCodec.class, PLAY, 0x19);
        registerPacketCodec(InAnimationCodec.class, PLAY, 0x27);
        registerPacketCodec(InTabCompleteCodec.class, PLAY, 0x06);
        registerPacketCodec(InPluginMessageCodec.class, PLAY, 0x0B);
        registerPacketCodec(PlayerCodec.class, PLAY, 0x14);

        // outbound
        registerPacketCodec(DisconnectCodec.class, PLAY, 0x1A);
        registerPacketCodec(DisconnectLoginCodec.class, LOGIN, 0x00);
        registerPacketCodec(SpawnMobCodec.class, PLAY, 0x03);
        registerPacketCodec(EncryptionResponseCodec.class, LOGIN, 0x01);
        registerPacketCodec(SetCompressionCodec.class, LOGIN, 0x03);
        registerPacketCodec(LoginSuccessCodec.class, LOGIN, 0x02);
        registerPacketCodec(ResponseCodec.class, STATUS, 0x00);
        registerPacketCodec(PongCodec.class, STATUS, 0x01);
        registerPacketCodec(JoinGameCodec.class, PLAY, 0x25);
        registerPacketCodec(SpawnPositionCodec.class, PLAY, 0x4D);
        registerPacketCodec(OutPlayerAbilitiesCodec.class, PLAY, 0x31);
        registerPacketCodec(OutPlayerPositionAndLookCodec.class, PLAY, 0x35);
        registerPacketCodec(ChunkDataCodec.class, PLAY, 0x21);
        registerPacketCodec(OutKeepAliveCodec.class, PLAY, 0x20);
        registerPacketCodec(OutTabCompleteCodec.class, PLAY, 0x10);
        registerPacketCodec(OutChatMessageCodec.class, PLAY, 0x0E);
        registerPacketCodec(WindowItemsCodec.class, PLAY, 0x14);
        registerPacketCodec(PlayerListItemCodec.class, PLAY, 0x33);
        registerPacketCodec(EntityLookCodec.class, PLAY, 0x2A);
        registerPacketCodec(EntityRelativeMoveCodec.class, PLAY, 0x28);
        registerPacketCodec(EntityLookAndRelativeMoveCodec.class, PLAY, 0x29);
        registerPacketCodec(SpawnPlayerCodec.class, PLAY, 0x05);
        registerPacketCodec(TimeUpdateCodec.class, PLAY, 0x4E);
        registerPacketCodec(UnloadChunkCodec.class, PLAY, 0x1D);
        registerPacketCodec(BlockChangeCodec.class, PLAY, 0x0B);
        registerPacketCodec(EntityTeleportCodec.class, PLAY, 0x50);
        registerPacketCodec(DestroyEntitiesCodec.class, PLAY, 0x37);
        registerPacketCodec(ChangeGameStateCodec.class, PLAY, 0x1E);
        registerPacketCodec(EntityHeadLookCodec.class, PLAY, 0x3B);
        registerPacketCodec(OutAnimationCodec.class, PLAY, 0x06);
        registerPacketCodec(SpawnObjectCodec.class, PLAY, 0x00);
        registerPacketCodec(OutPluginMessageCodec.class, PLAY, 0x18);
        registerPacketCodec(UpdateViewPositionCodec.class, PLAY, 0x40);
    }

    @Override
    public MinecraftClientProtocolLayer getCorrespondingClientLayer() {
        return MinecraftClientProtocolLayer.MINECRAFT_V1_14;
    }

    @Override
    public int getOrderedID() {
        return 2;
    }
}
