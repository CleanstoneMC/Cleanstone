package rocks.cleanstone.net.packet.minecraft;

import javax.annotation.Nullable;

import rocks.cleanstone.net.packet.OutboundPacket;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.inbound.AnimationPacket;
import rocks.cleanstone.net.packet.minecraft.inbound.ChatMessagePacket;
import rocks.cleanstone.net.packet.minecraft.inbound.CloseWindowPacket;
import rocks.cleanstone.net.packet.minecraft.inbound.HeldItemChangePacket;
import rocks.cleanstone.net.packet.minecraft.inbound.KeepAlivePacket;
import rocks.cleanstone.net.packet.minecraft.inbound.PlayerAbilitiesPacket;
import rocks.cleanstone.net.packet.minecraft.inbound.PlayerPositionAndLookPacket;
import rocks.cleanstone.net.packet.minecraft.inbound.PluginMessagePacket;
import rocks.cleanstone.net.packet.minecraft.inbound.TabCompletePacket;
import rocks.cleanstone.net.packet.minecraft.outbound.*;
import rocks.cleanstone.net.packet.protocol.ProtocolType;
import rocks.cleanstone.net.packet.protocol.StandardProtocolType;

public enum MinecraftOutboundPacketType implements PacketType {
    SPAWN_OBJECT(0, SpawnObjectPacket.class),
    SPAWN_EXPERIENCE_ORB(1, SpawnExperienceOrbPacket.class),
    SPAWN_GLOBAL_ENTITY(2, SpawnGlobalEntityPacket.class),
    SPAWN_MOB(3, SpawnMobPacket.class),
    SPAWN_PAINTING(4, SpawnPaintingPacket.class),
    SPAWN_PLAYER(5, SpawnPlayerPacket.class),
    ANIMATION(6, AnimationPacket.class),
    STATISTICS(7, StatisticsPacket.class),
    BLOCK_BREAK_ANIMATION(8, BlockBreakAnimationPacket.class),
    UPDATE_BLOCK_ENTITY(9, UpdateBlockEntityPacket.class),
    BLOCK_ACTION(10, BlockActionPacket.class),
    BLOCK_CHANGE(11, BlockChangePacket.class),
    BOSS_BAR(12, BossBarPacket.class),
    SERVER_DIFFICULTY(13, ServerDifficultyPacket.class),
    TAB_COMPLETE(14, TabCompletePacket.class),
    CHAT_MESSAGE(15, ChatMessagePacket.class),
    MULTI_BLOCK_CHANGE(16, MultiBlockChangePacket.class),
    CONFIRM_TRANSACTION(17, ConfirmTransactionPacket.class),
    CLOSE_WINDOW(18, CloseWindowPacket.class),
    OPEN_WINDOW(19, OpenWindowPacket.class),
    WINDOW_ITEMS(20, WindowItemsPacket.class),
    WINDOW_PROPERTY(21, WindowPropertyPacket.class),
    SET_SLOT(22, SetSlotPacket.class),
    SET_COOLDOWN(23, SetCooldownPacket.class),
    PLUGIN_MESSAGE(24, PluginMessagePacket.class),
    NAMED_SOUND_EFFECT(25, NamedSoundEffectPacket.class),
    DISCONNECT(26, DisconnectPacket.class),
    ENTITY_STATUS(27, EntityStatusPacket.class),
    EXPLOSION(28, ExplosionPacket.class),
    UNLOAD_CHUNK(29, UnloadChunkPacket.class),
    CHANGE_GAME_STATE(30, ChangeGameStatePacket.class),
    KEEP_ALIVE(31, KeepAlivePacket.class),
    CHUNK_DATA(32, ChunkDataPacket.class),
    EFFECT(33, EffectPacket.class),
    PARTICLE(34, ParticlePacket.class),
    JOIN_GAME(35, JoinGamePacket.class),
    MAP(36, MapPacket.class),
    ENTITY(37, EntityPacket.class),
    ENTITY_RELATIVE_MOVE(38, EntityRelativeMovePacket.class),
    ENTITY_LOOK_AND_RELATIVE_MOVE(39, EntityLookAndRelativeMovePacket.class),
    ENTITY_LOOK(40, EntityLookPacket.class),
    VEHICLE_MOVE(41, VehicleMovePacket.class),
    OPEN_SIGN_EDITOR(42, OpenSignEditorPacket.class),
    CRAFT_RECIPE_RESPONSE(43, CraftRecipeResponsePacket.class),
    PLAYER_ABILITIES(44, PlayerAbilitiesPacket.class),
    COMBAT_EVENT(45, CombatEventPacket.class),
    PLAYER_LIST_ITEM(46, PlayerListItemPacket.class),
    PLAYER_POSITION_AND_LOOK(47, PlayerPositionAndLookPacket.class),
    USE_BED(48, UseBedPacket.class),
    UNLOCK_RECIPES(49, UnlockRecipesPacket.class),
    DESTROY_ENTITIES(50, DestroyEntitiesPacket.class),
    REMOVE_ENTITY_EFFECT(51, RemoveEntityEffectPacket.class),
    RESOURCE_PACK_SEND(52, ResourcePackSendPacket.class),
    RESPAWN(53, RespawnPacket.class),
    ENTITY_HEAD_LOOK(54, EntityHeadLookPacket.class),
    SELECT_ADVANCEMENT_TAB(55, SelectAdvancementTabPacket.class),
    WORLD_BORDER(56, WorldBorderPacket.class),
    CAMERA(57, CameraPacket.class),
    HELD_ITEM_CHANGE(58, HeldItemChangePacket.class),
    DISPLAY_SCOREBOARD(59, DisplayScoreboardPacket.class),
    ENTITY_METADATA(60, EntityMetadataPacket.class),
    ATTACH_ENTITY(61, AttachEntityPacket.class),
    ENTITY_VELOCITY(62, EntityVelocityPacket.class),
    ENTITY_EQUIPMENT(63, EntityEquipmentPacket.class),
    SET_EXPERIENCE(64, SetExperiencePacket.class),
    UPDATE_HEALTH(65, UpdateHealthPacket.class),
    SCOREBOARD_OBJECTIVE(66, ScoreboardObjectivePacket.class),
    SET_PASSENGERS(67, SetPassengersPacket.class),
    TEAMS(68, TeamsPacket.class),
    UPDATE_SCORE(69, UpdateScorePacket.class),
    SPAWN_POSITION(70, SpawnPositionPacket.class),
    TIME_UPDATE(71, TimeUpdatePacket.class),
    TITLE(72, TitlePacket.class),
    SOUND_EFFECT(73, SoundEffectPacket.class),
    PLAYER_LIST_HEADER_AND_FOOTER(74, PlayerListHeaderAndFooterPacket.class),
    COLLECT_ITEM(75, CollectItemPacket.class),
    ENTITY_TELEPORT(76, EntityTeleportPacket.class),
    ADVANCEMENTS(77, AdvancementsPacket.class),
    ENTITY_PROPERTIES(78, EntityPropertiesPacket.class),
    ENTITY_EFFECT(79, EntityEffectPacket.class);

    private final int typeId;
    private final Class<? extends OutboundPacket> packetClass;

    MinecraftOutboundPacketType(int typeId, Class<? extends OutboundPacket> packetClass) {
        this.typeId = typeId;
        this.packetClass = packetClass;
    }

    @Nullable
    public static MinecraftOutboundPacketType byTypeId(int typeId) {
        for (MinecraftOutboundPacketType type : values()) {
            if (type.getTypeId() == typeId) return type;
        }
        return null;
    }

    @Override
    public int getTypeId() {
        return 4000 + typeId;
    }

    public Class<? extends OutboundPacket> getPacketClass() {
        return packetClass;
    }

    @Override
    public ProtocolType getProtocolType() {
        return StandardProtocolType.MINECRAFT;
    }
}
