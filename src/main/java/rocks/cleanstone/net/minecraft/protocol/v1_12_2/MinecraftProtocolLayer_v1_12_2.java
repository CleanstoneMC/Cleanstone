package rocks.cleanstone.net.minecraft.protocol.v1_12_2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.protocol.AutowiredServerProtocolLayer;
import rocks.cleanstone.net.minecraft.protocol.MinecraftClientProtocolLayer;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound.*;
import rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound.*;
import rocks.cleanstone.net.protocol.PacketCodec;

import java.util.List;

import static rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState.*;

@Component("minecraftProtocolLayer_v1_12_2")
public class MinecraftProtocolLayer_v1_12_2 extends AutowiredServerProtocolLayer {

    @Autowired
    public MinecraftProtocolLayer_v1_12_2(List<? extends PacketCodec> packetCodecs) {
        super(packetCodecs);

        //inbound
        registerPacketCodec(HandshakeCodec.class, HANDSHAKE, 0x00);
        registerPacketCodec(LoginStartCodec.class, LOGIN, 0x00);
        registerPacketCodec(EncryptionRequestCodec.class, LOGIN, 0x01);
        registerPacketCodec(RequestCodec.class, STATUS, 0x00);
        registerPacketCodec(PingCodec.class, STATUS, 0x01);
        registerPacketCodec(ClientSettingsCodec.class, PLAY, 0x04);
        registerPacketCodec(InKeepAliveCodec.class, PLAY, 0x0B);
        registerPacketCodec(InChatMessageCodec.class, PLAY, 0x02);
        registerPacketCodec(InTabCompleteCodec.class, PLAY, 0x01);
        registerPacketCodec(PlayerPositionCodec.class, PLAY, 0x0D);
        registerPacketCodec(PlayerLookCodec.class, PLAY, 0x0F);
        registerPacketCodec(PlayerPositionAndLookCodec.class, PLAY, 0x0E);
        registerPacketCodec(UseItemCodec.class, PLAY, 0x20);
        registerPacketCodec(PlayerBlockPlacementCodec.class, PLAY, 0x1F);
        registerPacketCodec(PlayerDiggingCodec.class, PLAY, 0x14);
        registerPacketCodec(CreativeInventoryActionCodec.class, PLAY, 0x1B);
        registerPacketCodec(HeldItemChangeCodec.class, PLAY, 0x1A);
        registerPacketCodec(InPlayerAbilitiesCodec.class, PLAY, 0x13);
        registerPacketCodec(InAnimationCodec.class, PLAY, 0x1D);
        registerPacketCodec(InPluginMessageCodec.class, PLAY, 0x09);
        registerPacketCodec(PlayerCodec.class, PLAY, 0x0C);

        //outbound
        registerPacketCodec(DisconnectCodec.class, PLAY, 0x1A);
        registerPacketCodec(DisconnectLoginCodec.class, LOGIN, 0x00);
        registerPacketCodec(SpawnMobCodec.class, PLAY, 0x03);
        registerPacketCodec(EncryptionResponseCodec.class, LOGIN, 0x01);
        registerPacketCodec(SetCompressionCodec.class, LOGIN, 0x03);
        registerPacketCodec(LoginSuccessCodec.class, LOGIN, 0x02);
        registerPacketCodec(ResponseCodec.class, STATUS, 0x00);
        registerPacketCodec(PongCodec.class, STATUS, 0x01);
        registerPacketCodec(JoinGameCodec.class, PLAY, 0x23);
        registerPacketCodec(SpawnPositionCodec.class, PLAY, 0x46);
        registerPacketCodec(OutPlayerAbilitiesCodec.class, PLAY, 0x2C);
        registerPacketCodec(OutPlayerPositionAndLookCodec.class, PLAY, 0x2F);
        registerPacketCodec(ChunkDataCodec.class, PLAY, 0x20);
        registerPacketCodec(OutKeepAliveCodec.class, PLAY, 0x1F);
        registerPacketCodec(OutTabCompleteCodec.class, PLAY, 0x0E);
        registerPacketCodec(OutChatMessageCodec.class, PLAY, 0x0F);
        registerPacketCodec(WindowItemsCodec.class, PLAY, 0x14);
        registerPacketCodec(PlayerListItemCodec.class, PLAY, 0x2E);
        registerPacketCodec(EntityLookCodec.class, PLAY, 0x28);
        registerPacketCodec(EntityRelativeMoveCodec.class, PLAY, 0x26);
        registerPacketCodec(EntityLookAndRelativeMoveCodec.class, PLAY, 0x27);
        registerPacketCodec(SpawnPlayerCodec.class, PLAY, 0x05);
        registerPacketCodec(TimeUpdateCodec.class, PLAY, 0x47);
        registerPacketCodec(UnloadChunkCodec.class, PLAY, 0x1D);
        registerPacketCodec(BlockChangeCodec.class, PLAY, 0x0B);
        registerPacketCodec(EntityTeleportCodec.class, PLAY, 0x4C);
        registerPacketCodec(DestroyEntitiesCodec.class, PLAY, 0x32);
        registerPacketCodec(ChangeGameStateCodec.class, PLAY, 0x1E);
        registerPacketCodec(EntityHeadLookCodec.class, PLAY, 0x36);
        registerPacketCodec(OutAnimationCodec.class, PLAY, 0x06);
        registerPacketCodec(SpawnObjectCodec.class, PLAY, 0x00);
        registerPacketCodec(OutPluginMessageCodec.class, PLAY, 0x18);
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
