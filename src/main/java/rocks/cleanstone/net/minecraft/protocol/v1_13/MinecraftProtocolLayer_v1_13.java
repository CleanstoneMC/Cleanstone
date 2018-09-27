package rocks.cleanstone.net.minecraft.protocol.v1_13;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.protocol.AutowiredServerProtocolLayer;
import rocks.cleanstone.net.minecraft.protocol.MinecraftClientProtocolLayer;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.*;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound.*;
import rocks.cleanstone.net.minecraft.protocol.v1_13.inbound.CreativeInventoryActionCodec;
import rocks.cleanstone.net.minecraft.protocol.v1_13.outbound.ChunkDataCodec;
import rocks.cleanstone.net.protocol.PacketCodec;

import java.util.List;

import static rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState.*;

@Component("minecraftProtocolLayer_v1_13")
public class MinecraftProtocolLayer_v1_13 extends AutowiredServerProtocolLayer {

    @Autowired
    public MinecraftProtocolLayer_v1_13(List<? extends PacketCodec> packetCodecs) {
        super(packetCodecs);
        // inbound
        registerPacketCodec(HandshakeCodec.class, HANDSHAKE, 0x00);
        registerPacketCodec(LoginStartCodec.class, LOGIN, 0x00);
        registerPacketCodec(EncryptionRequestCodec.class, LOGIN, 0x01);
        registerPacketCodec(RequestCodec.class, STATUS, 0x00);
        registerPacketCodec(PingCodec.class, STATUS, 0x01);
        registerPacketCodec(ClientSettingsCodec.class, PLAY, 0x04);
        registerPacketCodec(InKeepAliveCodec.class, PLAY, 0x0E);
        registerPacketCodec(InChatMessageCodec.class, PLAY, 0x02);
        registerPacketCodec(InTabCompleteCodec.class, PLAY, 0x05);
        registerPacketCodec(PlayerPositionCodec.class, PLAY, 0x10);
        registerPacketCodec(PlayerLookCodec.class, PLAY, 0x12);
        registerPacketCodec(PlayerPositionAndLookCodec.class, PLAY, 0x11);
        registerPacketCodec(UseItemCodec.class, PLAY, 0x2A);
        registerPacketCodec(PlayerBlockPlacementCodec.class, PLAY, 0x29);
        registerPacketCodec(PlayerDiggingCodec.class, PLAY, 0x18);
        registerPacketCodec(CreativeInventoryActionCodec.class, PLAY, 0x24);
        registerPacketCodec(HeldItemChangeCodec.class, PLAY, 0x21);
        registerPacketCodec(InPlayerAbilitiesCodec.class, PLAY, 0x17);
        registerPacketCodec(InAnimationCodec.class, PLAY, 0x27);
        registerPacketCodec(InTabCompleteCodec.class, PLAY, 0x05);

        // outbound
        registerPacketCodec(DisconnectCodec.class, PLAY, 0x1B);
        registerPacketCodec(DisconnectLoginCodec.class, LOGIN, 0x00);
        registerPacketCodec(SpawnMobCodec.class, PLAY, 0x03);
        registerPacketCodec(EncryptionResponseCodec.class, LOGIN, 0x01);
        registerPacketCodec(SetCompressionCodec.class, LOGIN, 0x03);
        registerPacketCodec(LoginSuccessCodec.class, LOGIN, 0x02);
        registerPacketCodec(ResponseCodec.class, STATUS, 0x00);
        registerPacketCodec(PongCodec.class, STATUS, 0x01);
        registerPacketCodec(JoinGameCodec.class, PLAY, 0x25);
        registerPacketCodec(SpawnPositionCodec.class, PLAY, 0x49);
        registerPacketCodec(OutPlayerAbilitiesCodec.class, PLAY, 0x2E);
        registerPacketCodec(OutPlayerPositionAndLookCodec.class, PLAY, 0x32);
        registerPacketCodec(ChunkDataCodec.class, PLAY, 0x22);
        registerPacketCodec(OutKeepAliveCodec.class, PLAY, 0x21);
        registerPacketCodec(OutTabCompleteCodec.class, PLAY, 0x10);
        registerPacketCodec(OutChatMessageCodec.class, PLAY, 0x0E);
        registerPacketCodec(WindowItemsCodec.class, PLAY, 0x15);
        registerPacketCodec(PlayerListItemCodec.class, PLAY, 0x30);
        registerPacketCodec(EntityLookCodec.class, PLAY, 0x2A);
        registerPacketCodec(EntityRelativeMoveCodec.class, PLAY, 0x28);
        registerPacketCodec(EntityLookAndRelativeMoveCodec.class, PLAY, 0x29);
        registerPacketCodec(SpawnPlayerCodec.class, PLAY, 0x05);
        registerPacketCodec(TimeUpdateCodec.class, PLAY, 0x47);
        registerPacketCodec(UnloadChunkCodec.class, PLAY, 0x1F);
        registerPacketCodec(BlockChangeCodec.class, PLAY, 0x0B);
        registerPacketCodec(EntityTeleportCodec.class, PLAY, 0x50);
        registerPacketCodec(DestroyEntitiesCodec.class, PLAY, 0x35);
        registerPacketCodec(ChangeGameStateCodec.class, PLAY, 0x20);
        registerPacketCodec(EntityHeadLookCodec.class, PLAY, 0x39);
        registerPacketCodec(OutAnimationCodec.class, PLAY, 0x06);
        registerPacketCodec(SpawnObjectCodec.class, PLAY, 0x00);
        registerPacketCodec(OutPluginMessageCodec.class, PLAY, 0x19);
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
