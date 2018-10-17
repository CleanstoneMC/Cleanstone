package rocks.cleanstone.net.minecraft.packet;

import rocks.cleanstone.net.minecraft.packet.inbound.*;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketDirection;
import rocks.cleanstone.net.packet.PacketType;

import javax.annotation.Nullable;

public enum MinecraftInboundPacketType implements PacketType {
    HANDSHAKE(HandshakePacket.class),
    CHAT_MESSAGE(InChatMessagePacket.class),
    CLICK_WINDOW(ClickWindowPacket.class),
    CLIENT_SETTINGS(ClientSettingsPacket.class),
    CLIENT_STATUS(ClientStatusPacket.class),
    CLOSE_WINDOW(CloseWindowPacket.class),
    CONFIRM_TRANSACTION(ConfirmTransactionPacket.class),
    ENCHANT_ITEM(EnchantItemPacket.class),
    KEEP_ALIVE(InKeepAlivePacket.class),
    TAB_COMPLETE(InTabCompletePacket.class),
    TELEPORT_CONFIRM(TeleportConfirmPacket.class),
    PLUGIN_MESSAGE(InPluginMessagePacket.class),
    USE_ENTITY(UseEntityPacket.class),
    PLAYER(PlayerPacket.class),
    PLAYER_POSITION(PlayerPositionPacket.class),
    PLAYER_POSITION_AND_LOOK(InPlayerPositionAndLookPacket.class),
    PLAYER_LOOK(PlayerLookPacket.class),
    STEER_BOAT(SteerBoatPacket.class),
    CRAFT_RECIPE_REQUEST(CraftRecipeRequestPacket.class),
    PLAYER_ABILITIES(InPlayerAbilitiesPacket.class),
    PLAYER_DIGGING(PlayerDiggingPacket.class),
    ENTITY_ACTION(EntityActionPacket.class),
    STEER_VEHICLE(SteerVehiclePacket.class),
    CRAFTING_BOOK_DATA(CraftingBookDataPacket.class),
    RESOURCE_PACK_STATUS(ResourcePackStatusPacket.class),
    ADVANCEMENT_TAB(AdvancementTabPacket.class),
    HELD_ITEM_CHANGE(HeldItemChangePacket.class),
    CREATIVE_INVENTORY_ACTION(CreativeInventoryActionPacket.class),
    UPDATE_SIGN(UpdateSignPacket.class),
    ANIMATION(InAnimationPacket.class),
    SPECTATE(SpectatePacket.class),
    PLAYER_BLOCK_PLACEMENT(PlayerBlockPlacementPacket.class),
    USE_ITEM(UseItemPacket.class),
    LOGIN_START(LoginStartPacket.class),
    ENCRYPTION_RESPONSE(EncryptionResponsePacket.class),
    REQUEST(RequestPacket.class),
    PING(PingPacket.class);

    private final Class<? extends Packet> packetClass;

    MinecraftInboundPacketType(Class<? extends Packet> packetClass) {
        this.packetClass = packetClass;
    }

    @Nullable
    public static MinecraftInboundPacketType byPacketClass(Class<? extends Packet> packetClass) {
        for (MinecraftInboundPacketType type : values()) {
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
