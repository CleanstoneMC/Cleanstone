package rocks.cleanstone.net.packet.minecraft;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.SendPacket;
import rocks.cleanstone.net.packet.protocol.ProtocolType;
import rocks.cleanstone.net.packet.protocol.StandardProtocolType;

public enum MinecraftSendPacketType implements PacketType {
    SPAWN_OBJECT(0, SpawnObjectPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    SPAWN_EXPERIENCE_ORB(1, SpawnExperienceOrbPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    SPAWN_GLOBAL_ENTITY(2, SpawnGlobalEntityPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    SPAWN_MOB(3, SpawnMobPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    SPAWN_PAINTING(4, SpawnPaintingPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    SPAWN_PLAYER(5, SpawnPlayerPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    ANIMATION(6, AnimationPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    STATISTICS(7, StatisticsPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    BLOCK_BREAK_ANIMATION(8, BlockBreakAnimationPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    UPDATE_BLOCK_ENTITY(9, UpdateBlockEntityPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    BLOCK_ACTION(10, BlockActionPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    BLOCK_CHANGE(11, BlockChangePacket.class, StandardProtocolType.MINECRAFT_LATEST),
    BOSS_BAR(12, BossBarPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    SERVER_DIFFICULTY(13, ServerDifficultyPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    TAB_COMPLETE(14, TabCompletePacket.class, StandardProtocolType.MINECRAFT_LATEST),
    CHAT_MESSAGE(15, ChatMessagePacket.class, StandardProtocolType.MINECRAFT_LATEST),
    MULTI_BLOCK_CHANGE(16, MultiBlockChangePacket.class, StandardProtocolType.MINECRAFT_LATEST),
    CONFIRM_TRANSACTION(17, ConfirmTransactionPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    CLOSE_WINDOW(18, CloseWindowPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    OPEN_WINDOW(19, OpenWindowPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    WINDOW_ITEMS(20, WindowItemsPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    WINDOW_PROPERTY(21, WindowPropertyPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    SET_SLOT(22, SetSlotPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    SET_COOLDOWN(23, SetCooldownPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    PLUGIN_MESSAGE(24, PluginMessagePacket.class, StandardProtocolType.MINECRAFT_LATEST),
    NAMED_SOUND_EFFECT(25, NamedSoundEffectPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    DISCONNECT(26, DisconnectPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    ENTITY_STATUS(27, EntityStatusPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    EXPLOSION(28, ExplosionPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    UNLOAD_CHUNK(29, UnloadChunkPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    CHANGE_GAME_STATE(30, ChangeGameStatePacket.class, StandardProtocolType.MINECRAFT_LATEST),
    KEEP_ALIVE(31, KeepAlivePacket.class, StandardProtocolType.MINECRAFT_LATEST),
    CHUNK_DATA(32, ChunkDataPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    EFFECT(33, EffectPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    PARTICLE(34, ParticlePacket.class, StandardProtocolType.MINECRAFT_LATEST),
    JOIN_GAME(35, JoinGamePacket.class, StandardProtocolType.MINECRAFT_LATEST),
    MAP(36, MapPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    ENTITY(37, EntityPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    ENTITY_RELATIVE_MOVE(38, EntityRelativeMovePacket.class, StandardProtocolType.MINECRAFT_LATEST),
    ENTITY_LOOK_AND_RELATIVE_MOVE(39, EntityLookAndRelativeMovePacket.class, StandardProtocolType.MINECRAFT_LATEST),
    ENTITY_LOOK(40, EntityLookPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    VEHICLE_MOVE(41, VehicleMovePacket.class, StandardProtocolType.MINECRAFT_LATEST),
    OPEN_SIGN_EDITOR(42, OpenSignEditorPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    CRAFT_RECIPE_RESPONSE(43, CraftRecipeResponsePacket.class, StandardProtocolType.MINECRAFT_LATEST),
    PLAYER_ABILITIES(44, PlayerAbilitiesPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    COMBAT_EVENT(45, CombatEventPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    PLAYER_LIST_ITEM(46, PlayerListItemPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    PLAYER_POSITION_AND_LOOK(47, PlayerPositionAndLookPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    USE_BED(48, UseBedPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    UNLOCK_RECIPES(49, UnlockRecipesPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    DESTROY_ENTITIES(50, DestroyEntitiesPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    REMOVE_ENTITY_EFFECT(51, RemoveEntityEffectPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    RESOURCE_PACK_SEND(52, ResourcePackSendPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    RESPAWN(53, RespawnPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    ENTITY_HEAD_LOOK(54, EntityHeadLookPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    SELECT_ADVANCEMENT_TAB(55, SelectAdvancementTabPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    WORLD_BORDER(56, WorldBorderPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    CAMERA(57, CameraPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    HELD_ITEM_CHANGE(58, HeldItemChangePacket.class, StandardProtocolType.MINECRAFT_LATEST),
    DISPLAY_SCOREBOARD(59, DisplayScoreboardPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    ENTITY_METADATA(60, EntityMetadataPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    ATTACH_ENTITY(61, AttachEntityPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    ENTITY_VELOCITY(62, EntityVelocityPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    ENTITY_EQUIPMENT(63, EntityEquipmentPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    SET_EXPERIENCE(64, SetExperiencePacket.class, StandardProtocolType.MINECRAFT_LATEST),
    UPDATE_HEALTH(65, UpdateHealthPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    SCOREBOARD_OBJECTIVE(66, ScoreboardObjectivePacket.class, StandardProtocolType.MINECRAFT_LATEST),
    SET_PASSENGERS(67, SetPassengersPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    TEAMS(68, TeamsPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    UPDATE_SCORE(69, UpdateScorePacket.class, StandardProtocolType.MINECRAFT_LATEST),
    SPAWN_POSITION(70, SpawnPositionPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    TIME_UPDATE(71, TimeUpdatePacket.class, StandardProtocolType.MINECRAFT_LATEST),
    TITLE(72, TitlePacket.class, StandardProtocolType.MINECRAFT_LATEST),
    SOUND_EFFECT(73, SoundEffectPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    PLAYER_LIST_HEADER_AND_FOOTER(74, PlayerListHeaderAndFooterPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    COLLECT_ITEM(75, CollectItemPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    ENTITY_TELEPORT(76, EntityTeleportPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    ADVANCEMENTS(77, AdvancementsPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    ENTITY_PROPERTIES(78, EntityPropertiesPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    ENTITY_EFFECT(79, EntityEffectPacket.class, StandardProtocolType.MINECRAFT_LATEST);

    private final int typeId;
    private final ProtocolType protocolType;
    private final Class<? extends SendPacket> packetClass;

    MinecraftSendPacketType(int typeId, Class<? extends SendPacket> packetClass, ProtocolType protocolType) {
        this.typeId = typeId;
        this.packetClass = packetClass;
        this.protocolType = protocolType;
    }

    @Override
    public int getTypeId() {
        return typeId;
    }

    @Override
    public ProtocolType getProtocolType() {
        return protocolType;
    }
}
