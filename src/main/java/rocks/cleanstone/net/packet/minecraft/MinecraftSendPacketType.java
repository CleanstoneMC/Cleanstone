package rocks.cleanstone.net.packet.minecraft;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.SendPacket;
import rocks.cleanstone.net.packet.minecraft.receive.AnimationPacket;
import rocks.cleanstone.net.packet.minecraft.receive.ChatMessagePacket;
import rocks.cleanstone.net.packet.minecraft.receive.CloseWindowPacket;
import rocks.cleanstone.net.packet.minecraft.receive.HeldItemChangePacket;
import rocks.cleanstone.net.packet.minecraft.receive.KeepAlivePacket;
import rocks.cleanstone.net.packet.minecraft.receive.PlayerAbilitiesPacket;
import rocks.cleanstone.net.packet.minecraft.receive.PlayerPositionAndLookPacket;
import rocks.cleanstone.net.packet.minecraft.receive.PluginMessagePacket;
import rocks.cleanstone.net.packet.minecraft.receive.TabCompletePacket;
import rocks.cleanstone.net.packet.minecraft.send.*;
import rocks.cleanstone.net.packet.protocol.ProtocolType;
import rocks.cleanstone.net.packet.protocol.StandardProtocolType;

public enum MinecraftSendPacketType implements PacketType {
    SPAWN_OBJECT(0, SpawnObjectPacket.class, StandardProtocolType.MINECRAFT),
    SPAWN_EXPERIENCE_ORB(1, SpawnExperienceOrbPacket.class, StandardProtocolType.MINECRAFT),
    SPAWN_GLOBAL_ENTITY(2, SpawnGlobalEntityPacket.class, StandardProtocolType.MINECRAFT),
    SPAWN_MOB(3, SpawnMobPacket.class, StandardProtocolType.MINECRAFT),
    SPAWN_PAINTING(4, SpawnPaintingPacket.class, StandardProtocolType.MINECRAFT),
    SPAWN_PLAYER(5, SpawnPlayerPacket.class, StandardProtocolType.MINECRAFT),
    ANIMATION(6, AnimationPacket.class, StandardProtocolType.MINECRAFT),
    STATISTICS(7, StatisticsPacket.class, StandardProtocolType.MINECRAFT),
    BLOCK_BREAK_ANIMATION(8, BlockBreakAnimationPacket.class, StandardProtocolType.MINECRAFT),
    UPDATE_BLOCK_ENTITY(9, UpdateBlockEntityPacket.class, StandardProtocolType.MINECRAFT),
    BLOCK_ACTION(10, BlockActionPacket.class, StandardProtocolType.MINECRAFT),
    BLOCK_CHANGE(11, BlockChangePacket.class, StandardProtocolType.MINECRAFT),
    BOSS_BAR(12, BossBarPacket.class, StandardProtocolType.MINECRAFT),
    SERVER_DIFFICULTY(13, ServerDifficultyPacket.class, StandardProtocolType.MINECRAFT),
    TAB_COMPLETE(14, TabCompletePacket.class, StandardProtocolType.MINECRAFT),
    CHAT_MESSAGE(15, ChatMessagePacket.class, StandardProtocolType.MINECRAFT),
    MULTI_BLOCK_CHANGE(16, MultiBlockChangePacket.class, StandardProtocolType.MINECRAFT),
    CONFIRM_TRANSACTION(17, ConfirmTransactionPacket.class, StandardProtocolType.MINECRAFT),
    CLOSE_WINDOW(18, CloseWindowPacket.class, StandardProtocolType.MINECRAFT),
    OPEN_WINDOW(19, OpenWindowPacket.class, StandardProtocolType.MINECRAFT),
    WINDOW_ITEMS(20, WindowItemsPacket.class, StandardProtocolType.MINECRAFT),
    WINDOW_PROPERTY(21, WindowPropertyPacket.class, StandardProtocolType.MINECRAFT),
    SET_SLOT(22, SetSlotPacket.class, StandardProtocolType.MINECRAFT),
    SET_COOLDOWN(23, SetCooldownPacket.class, StandardProtocolType.MINECRAFT),
    PLUGIN_MESSAGE(24, PluginMessagePacket.class, StandardProtocolType.MINECRAFT),
    NAMED_SOUND_EFFECT(25, NamedSoundEffectPacket.class, StandardProtocolType.MINECRAFT),
    DISCONNECT(26, DisconnectPacket.class, StandardProtocolType.MINECRAFT),
    ENTITY_STATUS(27, EntityStatusPacket.class, StandardProtocolType.MINECRAFT),
    EXPLOSION(28, ExplosionPacket.class, StandardProtocolType.MINECRAFT),
    UNLOAD_CHUNK(29, UnloadChunkPacket.class, StandardProtocolType.MINECRAFT),
    CHANGE_GAME_STATE(30, ChangeGameStatePacket.class, StandardProtocolType.MINECRAFT),
    KEEP_ALIVE(31, KeepAlivePacket.class, StandardProtocolType.MINECRAFT),
    CHUNK_DATA(32, ChunkDataPacket.class, StandardProtocolType.MINECRAFT),
    EFFECT(33, EffectPacket.class, StandardProtocolType.MINECRAFT),
    PARTICLE(34, ParticlePacket.class, StandardProtocolType.MINECRAFT),
    JOIN_GAME(35, JoinGamePacket.class, StandardProtocolType.MINECRAFT),
    MAP(36, MapPacket.class, StandardProtocolType.MINECRAFT),
    ENTITY(37, EntityPacket.class, StandardProtocolType.MINECRAFT),
    ENTITY_RELATIVE_MOVE(38, EntityRelativeMovePacket.class, StandardProtocolType.MINECRAFT),
    ENTITY_LOOK_AND_RELATIVE_MOVE(39, EntityLookAndRelativeMovePacket.class, StandardProtocolType.MINECRAFT),
    ENTITY_LOOK(40, EntityLookPacket.class, StandardProtocolType.MINECRAFT),
    VEHICLE_MOVE(41, VehicleMovePacket.class, StandardProtocolType.MINECRAFT),
    OPEN_SIGN_EDITOR(42, OpenSignEditorPacket.class, StandardProtocolType.MINECRAFT),
    CRAFT_RECIPE_RESPONSE(43, CraftRecipeResponsePacket.class, StandardProtocolType.MINECRAFT),
    PLAYER_ABILITIES(44, PlayerAbilitiesPacket.class, StandardProtocolType.MINECRAFT),
    COMBAT_EVENT(45, CombatEventPacket.class, StandardProtocolType.MINECRAFT),
    PLAYER_LIST_ITEM(46, PlayerListItemPacket.class, StandardProtocolType.MINECRAFT),
    PLAYER_POSITION_AND_LOOK(47, PlayerPositionAndLookPacket.class, StandardProtocolType.MINECRAFT),
    USE_BED(48, UseBedPacket.class, StandardProtocolType.MINECRAFT),
    UNLOCK_RECIPES(49, UnlockRecipesPacket.class, StandardProtocolType.MINECRAFT),
    DESTROY_ENTITIES(50, DestroyEntitiesPacket.class, StandardProtocolType.MINECRAFT),
    REMOVE_ENTITY_EFFECT(51, RemoveEntityEffectPacket.class, StandardProtocolType.MINECRAFT),
    RESOURCE_PACK_SEND(52, ResourcePackSendPacket.class, StandardProtocolType.MINECRAFT),
    RESPAWN(53, RespawnPacket.class, StandardProtocolType.MINECRAFT),
    ENTITY_HEAD_LOOK(54, EntityHeadLookPacket.class, StandardProtocolType.MINECRAFT),
    SELECT_ADVANCEMENT_TAB(55, SelectAdvancementTabPacket.class, StandardProtocolType.MINECRAFT),
    WORLD_BORDER(56, WorldBorderPacket.class, StandardProtocolType.MINECRAFT),
    CAMERA(57, CameraPacket.class, StandardProtocolType.MINECRAFT),
    HELD_ITEM_CHANGE(58, HeldItemChangePacket.class, StandardProtocolType.MINECRAFT),
    DISPLAY_SCOREBOARD(59, DisplayScoreboardPacket.class, StandardProtocolType.MINECRAFT),
    ENTITY_METADATA(60, EntityMetadataPacket.class, StandardProtocolType.MINECRAFT),
    ATTACH_ENTITY(61, AttachEntityPacket.class, StandardProtocolType.MINECRAFT),
    ENTITY_VELOCITY(62, EntityVelocityPacket.class, StandardProtocolType.MINECRAFT),
    ENTITY_EQUIPMENT(63, EntityEquipmentPacket.class, StandardProtocolType.MINECRAFT),
    SET_EXPERIENCE(64, SetExperiencePacket.class, StandardProtocolType.MINECRAFT),
    UPDATE_HEALTH(65, UpdateHealthPacket.class, StandardProtocolType.MINECRAFT),
    SCOREBOARD_OBJECTIVE(66, ScoreboardObjectivePacket.class, StandardProtocolType.MINECRAFT),
    SET_PASSENGERS(67, SetPassengersPacket.class, StandardProtocolType.MINECRAFT),
    TEAMS(68, TeamsPacket.class, StandardProtocolType.MINECRAFT),
    UPDATE_SCORE(69, UpdateScorePacket.class, StandardProtocolType.MINECRAFT),
    SPAWN_POSITION(70, SpawnPositionPacket.class, StandardProtocolType.MINECRAFT),
    TIME_UPDATE(71, TimeUpdatePacket.class, StandardProtocolType.MINECRAFT),
    TITLE(72, TitlePacket.class, StandardProtocolType.MINECRAFT),
    SOUND_EFFECT(73, SoundEffectPacket.class, StandardProtocolType.MINECRAFT),
    PLAYER_LIST_HEADER_AND_FOOTER(74, PlayerListHeaderAndFooterPacket.class, StandardProtocolType.MINECRAFT),
    COLLECT_ITEM(75, CollectItemPacket.class, StandardProtocolType.MINECRAFT),
    ENTITY_TELEPORT(76, EntityTeleportPacket.class, StandardProtocolType.MINECRAFT),
    ADVANCEMENTS(77, AdvancementsPacket.class, StandardProtocolType.MINECRAFT),
    ENTITY_PROPERTIES(78, EntityPropertiesPacket.class, StandardProtocolType.MINECRAFT),
    ENTITY_EFFECT(79, EntityEffectPacket.class, StandardProtocolType.MINECRAFT);

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

    public Class<? extends SendPacket> getPacketClass() {
        return packetClass;
    }

    @Override
    public ProtocolType getProtocolType() {
        return protocolType;
    }
}
