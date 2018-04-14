package rocks.cleanstone.net.packet.minecraft;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.ReceivePacket;
import rocks.cleanstone.net.packet.minecraft.receive.*;
import rocks.cleanstone.net.packet.protocol.ProtocolType;
import rocks.cleanstone.net.packet.protocol.StandardProtocolType;

public enum MinecraftReceivePacketType implements PacketType {
    HANDSHAKE(0, HandshakePacket.class, StandardProtocolType.MINECRAFT_LATEST),
    CHAT_MESSAGE(1, HandshakePacket.class, StandardProtocolType.MINECRAFT_LATEST),
    CLICK_WINDOW(2, ClickWindowPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    CLIENT_SETTINGS(3, ClientSettingsPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    CLIENT_STATUS(4, ClientStatusPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    CLOSE_WINDOW(5, CloseWindowPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    CONFIRM_TRANSACTION(6, ConfirmTransaction.class, StandardProtocolType.MINECRAFT_LATEST),
    ENCHANT_ITEM(7, EnchantItemPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    KEEP_ALIVE(8, KeepAlivePacket.class, StandardProtocolType.MINECRAFT_LATEST),
    TAB_COMPLETE(9, TabCompletePacket.class, StandardProtocolType.MINECRAFT_LATEST),
    TELEPORT_CONFIRM(10, TeleportConfirmPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    PLUGIN_MESSAGE(11, PluginMessagePacket.class, StandardProtocolType.MINECRAFT_LATEST),
    USE_ENTITY(12, UseEntityPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    PLAYER(13, PlayerPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    PLAYER_POSITION(14, PlayerPositionPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    PLAYER_POSITION_AND_LOOK(15, PlayerPositionAndLookPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    PLAYER_LOOK(16, PlayerLookPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    STEER_BOAT(17, SteerBoatPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    CRAFT_RECIPE_REQUEST(18, CraftRecipeRequestPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    PLAYER_ABILITIES(19, PlayerAbilitiesPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    PLAYER_DIGGING(20, PlayerDiggingPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    ENTITY_ACTION(21, EntityActionPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    STEER_VEHICLE(22, SteerVehiclePacket.class, StandardProtocolType.MINECRAFT_LATEST),
    CRAFTING_BOOK_DATA(23, CraftingBookDataPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    RESOURCE_PACK_STATUS(24, ResourcePackStatusPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    ADVANCEMENT_TAB(25, AdvancementTabPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    HELD_ITEM_CHANGE(26, HeldItemChangePacket.class, StandardProtocolType.MINECRAFT_LATEST),
    CREATIVE_INVENTORY_ACTION(27, CreativeInventoryActionPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    UPDATE_SIGN(28, UpdateSignPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    ANIMATION(29, AnimationPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    SPECTATE(30, SpectatePacket.class, StandardProtocolType.MINECRAFT_LATEST),
    PLAYER_BLOCK_PLACEMENT(31, PlayerBlockPlacementPacket.class, StandardProtocolType.MINECRAFT_LATEST),
    USE_ITEM(32, UseItemPacket.class, StandardProtocolType.MINECRAFT_LATEST);

    private final int typeId;
    private final ProtocolType protocolType;
    private final Class<? extends ReceivePacket> packetClass;

    MinecraftReceivePacketType(int typeId, Class<? extends ReceivePacket> packetClass, ProtocolType protocolType) {
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
