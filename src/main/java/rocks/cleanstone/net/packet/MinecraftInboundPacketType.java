package rocks.cleanstone.net.packet;

import rocks.cleanstone.net.packet.inbound.*;

import javax.annotation.Nullable;

public enum MinecraftInboundPacketType implements PacketType {
    HANDSHAKE(0, HandshakePacket.class),
    CHAT_MESSAGE(1, InChatMessagePacket.class),
    CLICK_WINDOW(2, ClickWindowPacket.class),
    CLIENT_SETTINGS(3, ClientSettingsPacket.class),
    CLIENT_STATUS(4, ClientStatusPacket.class),
    CLOSE_WINDOW(5, CloseWindowPacket.class),
    CONFIRM_TRANSACTION(6, ConfirmTransactionPacket.class),
    ENCHANT_ITEM(7, EnchantItemPacket.class),
    KEEP_ALIVE(8, InKeepAlivePacket.class),
    TAB_COMPLETE(9, InTabCompletePacket.class),
    TELEPORT_CONFIRM(10, TeleportConfirmPacket.class),
    PLUGIN_MESSAGE(11, PluginMessagePacket.class),
    USE_ENTITY(12, UseEntityPacket.class),
    PLAYER(13, PlayerPacket.class),
    PLAYER_POSITION(14, PlayerPositionPacket.class),
    PLAYER_POSITION_AND_LOOK(15, InPlayerPositionAndLookPacket.class),
    PLAYER_LOOK(16, PlayerLookPacket.class),
    STEER_BOAT(17, SteerBoatPacket.class),
    CRAFT_RECIPE_REQUEST(18, CraftRecipeRequestPacket.class),
    PLAYER_ABILITIES(19, InPlayerAbilitiesPacket.class),
    PLAYER_DIGGING(20, PlayerDiggingPacket.class),
    ENTITY_ACTION(21, EntityActionPacket.class),
    STEER_VEHICLE(22, SteerVehiclePacket.class),
    CRAFTING_BOOK_DATA(23, CraftingBookDataPacket.class),
    RESOURCE_PACK_STATUS(24, ResourcePackStatusPacket.class),
    ADVANCEMENT_TAB(25, AdvancementTabPacket.class),
    HELD_ITEM_CHANGE(26, HeldItemChangePacket.class),
    CREATIVE_INVENTORY_ACTION(27, CreativeInventoryActionPacket.class),
    UPDATE_SIGN(28, UpdateSignPacket.class),
    ANIMATION(29, AnimationPacket.class),
    SPECTATE(30, SpectatePacket.class),
    PLAYER_BLOCK_PLACEMENT(31, PlayerBlockPlacementPacket.class),
    USE_ITEM(32, UseItemPacket.class),
    LOGIN_START(33, LoginStartPacket.class),
    ENCRYPTION_RESPONSE(34, EncryptionResponsePacket.class),
    REQUEST(35, RequestPacket.class),
    PING(36, PingPacket.class);


    private final int typeID;
    private final Class<? extends Packet> packetClass;

    MinecraftInboundPacketType(int typeID, Class<? extends Packet> packetClass) {
        this.typeID = typeID;
        this.packetClass = packetClass;
    }

    @Nullable
    public static MinecraftInboundPacketType byPacketClass(Class<? extends Packet> packetClass) {
        for (MinecraftInboundPacketType type : values()) {
            if (type.getPacketClass() == packetClass) return type;
        }
        return null;
    }

    @Override
    public int getTypeID() {
        return 3000 + typeID;
    }

    public Class<? extends Packet> getPacketClass() {
        return packetClass;
    }

    @Override
    public PacketDirection getDirection() {
        return PacketDirection.INBOUND;
    }
}
