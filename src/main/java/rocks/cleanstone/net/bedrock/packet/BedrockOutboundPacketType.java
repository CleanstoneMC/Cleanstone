package rocks.cleanstone.net.bedrock.packet;

import rocks.cleanstone.net.bedrock.packet.outbound.*;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketDirection;
import rocks.cleanstone.net.packet.PacketType;

import javax.annotation.Nullable;

public enum BedrockOutboundPacketType implements PacketType {
    PLAY_STATUS(PlayStatusPacket.class),
    SERVER_TO_CLIENT_HANDSHAKE(ServerToClientHandshakePacket.class),
    DISCONNECT(DisconnectPacket.class),
    RESOURCE_PACKS_INFO(ResourcePacksInfoPacket.class),
    RESOURCE_PACK_STACK(ResourcePackStackPacket.class),
    SET_TIME(SetTimePacket.class),
    START_GAME(StartGamePacket.class),
    ADD_PLAYER(AddPlayerPacket.class),
    ADD_ENTITY(AddEntityPacket.class),
    REMOVE_ENTITY(RemoveEntityPacket.class),
    ADD_ITEM_ENTITY(AddItemEntityPacket.class),
    TAKE_ITEM_ENTITY(TakeItemEntityPacket.class),
    UPDATE_BLOCK(UpdateBlockPacket.class),
    ADD_PAINTING(AddPaintingPacket.class),
    EXPLODE(ExplodePacket.class),
    LEVEL_EVENT(LevelEventPacket.class),
    BLOCK_EVENT(BlockEventPacket.class),
    MOB_EFFECT(MobEffectPacket.class),
    UPDATE_ATTRIBUTES(UpdateAttributesPacket.class),
    HURT_ARMOR(HurtArmorPacket.class),
    SET_ENTITY_LINK(SetEntityLinkPacket.class),
    SET_HEALTH(SetHealthPacket.class),
    SET_SPAWN_POSITION(SetSpawnPositionPacket.class),
    CONTAINER_OPEN(ContainerOpenPacket.class),
    CONTAINER_SET_DATA(ContainerSetDataPacket.class),
    CRAFTING_DATA(CraftingDataPacket.class),
    GUI_DATA_PICK_ITEM(GuiDataPickItemPacket.class),
    FULL_CHUNK_DATA(FullChunkDataPacket.class),
    SET_COMMANDS_ENABLED(SetCommandsEnabledPacket.class),
    SET_DIFFICULTY(SetDifficultyPacket.class),
    CHANGE_DIMENSION(ChangeDimensionPacket.class),
    PLAYER_LIST(PlayerListPacket.class),
    SIMPLE_EVENT(SimpleEventPacket.class),
    TELEMETRY_EVENT(TelemetryEventPacket.class),
    SPAWN_EXPERIENCE_ORB(SpawnExperienceOrbPacket.class),
    CLIENTBOUND_MAP_ITEM_DATA_(ClientboundMapItemDataPacket.class),
    CHUNK_RADIUS_UPDATE(ChunkRadiusUpdatePacket.class),
    GAME_RULES_CHANGED(GameRulesChangedPacket.class),
    CAMERA(CameraPacket.class),
    BOSS_EVENT(BossEventPacket.class),
    SHOW_CREDITS(ShowCreditsPacket.class),
    AVAILABLE_COMMANDS(AvailableCommandsPacket.class),
    COMMAND_OUTPUT(CommandOutputPacket.class),
    UPDATE_TRADE(UpdateTradePacket.class),
    UPDATE_EQUIPMENT(UpdateEquipmentPacket.class),
    RESOURCE_PACK_DATA_INFO(ResourcePackDataInfoPacket.class),
    RESOURCE_PACK_CHUNK_DATA(ResourcePackChunkDataPacket.class),
    TRANSFER(TransferPacket.class),
    PLAY_SOUND(PlaySoundPacket.class),
    STOP_SOUND(StopSoundPacket.class),
    SET_TITLE(SetTitlePacket.class),
    ADD_BEHAVIOR_TREE(AddBehaviorTreePacket.class),
    STRUCTURE_BLOCK_UPDATE(StructureBlockUpdatePacket.class),
    SHOW_STORE_OFFER(ShowStoreOfferPacket.class),
    SUB_CLIENT_LOGIN(SubClientLoginPacket.class),
    INITIATE_WEB_SOCKET_CONNECTION(InitiateWebSocketConnectionPacket.class),
    SET_LAST_HURT_BY(SetLastHurtByPacket.class),
    BOOK_EDIT(BookEditPacket.class),
    MODAL_FORM_REQUEST(ModalFormRequestPacket.class),
    SERVER_SETTINGS_RESPONSE(ServerSettingsResponsePacket.class),
    SHOW_PROFILE(ShowProfilePacket.class),
    SET_DEFAULT_GAME_TYPE(SetDefaultGameTypePacket.class),
    REMOVE_OBJECTIVE(RemoveObjectivePacket.class),
    SET_DISPLAY_OBJECTIVE(SetDisplayObjectivePacket.class),
    SET_SCORE(SetScorePacket.class),
    UPDATE_BLOCK_SYNCED(UpdateBlockSyncedPacket.class),
    MOVE_ENTITY_DELTA(MoveEntityDeltaPacket.class),
    SET_SCOREBOARD_IDENTITY_PACKET(SetScoreboardIdentityPacketPacket.class),
    UPDATE_SOFT_ENUM_PACKET(UpdateSoftEnumPacketPacket.class),
    NETWORK_STACK_LATENCY_PACKET(NetworkStackLatencyPacketPacket.class);

    private final Class<? extends Packet> packetClass;

    BedrockOutboundPacketType(Class<? extends Packet> packetClass) {
        this.packetClass = packetClass;
    }

    @Nullable
    public static BedrockOutboundPacketType byPacketClass(Class<? extends Packet> packetClass) {
        for (BedrockOutboundPacketType type : values()) {
            if (type.getPacketClass() == packetClass) return type;
        }
        return null;
    }

    public Class<? extends Packet> getPacketClass() {
        return packetClass;
    }

    @Override
    public PacketDirection getDirection() {
        return PacketDirection.INBOUND;
    }
}
