package rocks.cleanstone.net.minecraft.packet;

import rocks.cleanstone.net.minecraft.packet.outbound.*;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketDirection;
import rocks.cleanstone.net.packet.PacketType;

public enum MinecraftOutboundPacketType implements PacketType {
    SPAWN_OBJECT(SpawnObjectPacket.class),
    SPAWN_EXPERIENCE_ORB(SpawnExperienceOrbPacket.class),
    SPAWN_GLOBAL_ENTITY(SpawnGlobalEntityPacket.class),
    SPAWN_MOB(SpawnMobPacket.class),
    SPAWN_PAINTING(SpawnPaintingPacket.class),
    SPAWN_PLAYER(SpawnPlayerPacket.class),
    ANIMATION(AnimationPacket.class),
    STATISTICS(StatisticsPacket.class),
    BLOCK_BREAK_ANIMATION(BlockBreakAnimationPacket.class),
    UPDATE_BLOCK_ENTITY(UpdateBlockEntityPacket.class),
    BLOCK_ACTION(BlockActionPacket.class),
    BLOCK_CHANGE(BlockChangePacket.class),
    BOSS_BAR(BossBarPacket.class),
    SERVER_DIFFICULTY(ServerDifficultyPacket.class),
    TAB_COMPLETE(OutTabCompletePacket.class),
    CHAT_MESSAGE(OutChatMessagePacket.class),
    MULTI_BLOCK_CHANGE(MultiBlockChangePacket.class),
    CONFIRM_TRANSACTION(ConfirmTransactionPacket.class),
    CLOSE_WINDOW(CloseWindowPacket.class),
    OPEN_WINDOW(OpenWindowPacket.class),
    WINDOW_ITEMS(WindowItemsPacket.class),
    WINDOW_PROPERTY(WindowPropertyPacket.class),
    SET_SLOT(SetSlotPacket.class),
    SET_COOLDOWN(SetCooldownPacket.class),
    PLUGIN_MESSAGE(OutPluginMessagePacket.class),
    NAMED_SOUND_EFFECT(NamedSoundEffectPacket.class),
    DISCONNECT(DisconnectPacket.class),
    ENTITY_STATUS(EntityStatusPacket.class),
    EXPLOSION(ExplosionPacket.class),
    UNLOAD_CHUNK(UnloadChunkPacket.class),
    CHANGE_GAME_STATE(ChangeGameStatePacket.class),
    KEEP_ALIVE(OutKeepAlivePacket.class),
    CHUNK_DATA(ChunkDataPacket.class),
    EFFECT(EffectPacket.class),
    PARTICLE(ParticlePacket.class),
    JOIN_GAME(JoinGamePacket.class),
    //    MAP(MapPacket.class),
//    ENTITY(EntityPacket.class),
    ENTITY_RELATIVE_MOVE(EntityRelativeMovePacket.class),
    ENTITY_LOOK_AND_RELATIVE_MOVE(EntityLookAndRelativeMovePacket.class),
    ENTITY_LOOK(EntityLookPacket.class),
    //    VEHICLE_MOVE(VehicleMovePacket.class),
//    OPEN_SIGN_EDITOR(OpenSignEditorPacket.class),
//    CRAFT_RECIPE_RESPONSE(CraftRecipeResponsePacket.class),
    PLAYER_ABILITIES(OutPlayerAbilitiesPacket.class),
    //    COMBAT_EVENT(CombatEventPacket.class),
    PLAYER_LIST_ITEM(PlayerListItemPacket.class),
    PLAYER_POSITION_AND_LOOK(OutPlayerPositionAndLookPacket.class),
    //    USE_BED(UseBedPacket.class),
//    UNLOCK_RECIPES(UnlockRecipesPacket.class),
    DESTROY_ENTITIES(DestroyEntitiesPacket.class),
    //    REMOVE_ENTITY_EFFECT(RemoveEntityEffectPacket.class),
//    RESOURCE_PACK_SEND(ResourcePackSendPacket.class),
//    RESPAWN(RespawnPacket.class),
    ENTITY_HEAD_LOOK(EntityHeadLookPacket.class),
    //    SELECT_ADVANCEMENT_TAB(SelectAdvancementTabPacket.class),
//    WORLD_BORDER(WorldBorderPacket.class),
//    CAMERA(CameraPacket.class),
//    HELD_ITEM_CHANGE(HeldItemChangePacket.class),
//    DISPLAY_SCOREBOARD(DisplayScoreboardPacket.class),
    ENTITY_METADATA(EntityMetadataPacket.class),
    //    ATTACH_ENTITY(AttachEntityPacket.class),
//    ENTITY_VELOCITY(EntityVelocityPacket.class),
//    ENTITY_EQUIPMENT(EntityEquipmentPacket.class),
//    SET_EXPERIENCE(SetExperiencePacket.class),
//    UPDATE_HEALTH(UpdateHealthPacket.class),
//    SCOREBOARD_OBJECTIVE(ScoreboardObjectivePacket.class),
//    SET_PASSENGERS(SetPassengersPacket.class),
//    TEAMS(TeamsPacket.class),
//    UPDATE_SCORE(UpdateScorePacket.class),
    SPAWN_POSITION(SpawnPositionPacket.class),
    TIME_UPDATE(TimeUpdatePacket.class),
    //    TITLE(TitlePacket.class),
//    SOUND_EFFECT(SoundEffectPacket.class),
//    PLAYER_LIST_HEADER_AND_FOOTER(PlayerListHeaderAndFooterPacket.class),
//    COLLECT_ITEM(CollectItemPacket.class),
    ENTITY_TELEPORT(EntityTeleportPacket.class),
    //    ADVANCEMENTS(AdvancementsPacket.class),
//    ENTITY_PROPERTIES(EntityPropertiesPacket.class),
//    ENTITY_EFFECT(EntityEffectPacket.class),
    DISCONNECT_LOGIN(DisconnectLoginPacket.class),
    ENCRYPTION_REQUEST(EncryptionRequestPacket.class),
    SET_COMPRESSION(SetCompressionPacket.class),
    LOGIN_SUCCESS(LoginSuccessPacket.class),
    RESPONSE(ResponsePacket.class),
    PONG(PongPacket.class),
    UPDATE_VIEW_POSITION(UpdateViewPositionPacket.class);

    private final Class<? extends Packet> packetClass;

    MinecraftOutboundPacketType(Class<? extends Packet> packetClass) {
        this.packetClass = packetClass;
    }

    public Class<? extends Packet> getPacketClass() {
        return packetClass;
    }

    @Override
    public PacketDirection getDirection() {
        return PacketDirection.OUTBOUND;
    }
}
