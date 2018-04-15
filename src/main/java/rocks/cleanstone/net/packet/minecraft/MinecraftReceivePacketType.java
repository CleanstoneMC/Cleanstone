package rocks.cleanstone.net.packet.minecraft;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.ReceivePacket;
import rocks.cleanstone.net.packet.minecraft.receive.*;
import rocks.cleanstone.net.packet.protocol.ProtocolType;
import rocks.cleanstone.net.packet.protocol.StandardProtocolType;

public enum MinecraftReceivePacketType implements PacketType {
    HANDSHAKE(0, HandshakePacket.class, StandardProtocolType.MINECRAFT),
    CHAT_MESSAGE(1, HandshakePacket.class, StandardProtocolType.MINECRAFT),
    CLICK_WINDOW(2, ClickWindowPacket.class, StandardProtocolType.MINECRAFT),
    CLIENT_SETTINGS(3, ClientSettingsPacket.class, StandardProtocolType.MINECRAFT),
    CLIENT_STATUS(4, ClientStatusPacket.class, StandardProtocolType.MINECRAFT),
    CLOSE_WINDOW(5, CloseWindowPacket.class, StandardProtocolType.MINECRAFT),
    CONFIRM_TRANSACTION(6, ConfirmTransaction.class, StandardProtocolType.MINECRAFT),
    ENCHANT_ITEM(7, EnchantItemPacket.class, StandardProtocolType.MINECRAFT),
    KEEP_ALIVE(8, KeepAlivePacket.class, StandardProtocolType.MINECRAFT),
    TAB_COMPLETE(9, TabCompletePacket.class, StandardProtocolType.MINECRAFT),
    TELEPORT_CONFIRM(10, TeleportConfirmPacket.class, StandardProtocolType.MINECRAFT),
    PLUGIN_MESSAGE(11, PluginMessagePacket.class, StandardProtocolType.MINECRAFT),
    USE_ENTITY(12, UseEntityPacket.class, StandardProtocolType.MINECRAFT),
    PLAYER(13, PlayerPacket.class, StandardProtocolType.MINECRAFT),
    PLAYER_POSITION(14, PlayerPositionPacket.class, StandardProtocolType.MINECRAFT),
    PLAYER_POSITION_AND_LOOK(15, PlayerPositionAndLookPacket.class, StandardProtocolType.MINECRAFT),
    PLAYER_LOOK(16, PlayerLookPacket.class, StandardProtocolType.MINECRAFT),
    STEER_BOAT(17, SteerBoatPacket.class, StandardProtocolType.MINECRAFT),
    CRAFT_RECIPE_REQUEST(18, CraftRecipeRequestPacket.class, StandardProtocolType.MINECRAFT),
    PLAYER_ABILITIES(19, PlayerAbilitiesPacket.class, StandardProtocolType.MINECRAFT),
    PLAYER_DIGGING(20, PlayerDiggingPacket.class, StandardProtocolType.MINECRAFT),
    ENTITY_ACTION(21, EntityActionPacket.class, StandardProtocolType.MINECRAFT),
    STEER_VEHICLE(22, SteerVehiclePacket.class, StandardProtocolType.MINECRAFT),
    CRAFTING_BOOK_DATA(23, CraftingBookDataPacket.class, StandardProtocolType.MINECRAFT),
    RESOURCE_PACK_STATUS(24, ResourcePackStatusPacket.class, StandardProtocolType.MINECRAFT),
    ADVANCEMENT_TAB(25, AdvancementTabPacket.class, StandardProtocolType.MINECRAFT),
    HELD_ITEM_CHANGE(26, HeldItemChangePacket.class, StandardProtocolType.MINECRAFT),
    CREATIVE_INVENTORY_ACTION(27, CreativeInventoryActionPacket.class, StandardProtocolType.MINECRAFT),
    UPDATE_SIGN(28, UpdateSignPacket.class, StandardProtocolType.MINECRAFT),
    ANIMATION(29, AnimationPacket.class, StandardProtocolType.MINECRAFT),
    SPECTATE(30, SpectatePacket.class, StandardProtocolType.MINECRAFT),
    PLAYER_BLOCK_PLACEMENT(31, PlayerBlockPlacementPacket.class, StandardProtocolType.MINECRAFT),
    USE_ITEM(32, UseItemPacket.class, StandardProtocolType.MINECRAFT);

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

    public Class<? extends ReceivePacket> getPacketClass() {
        return packetClass;
    }

    @Override
    public ProtocolType getProtocolType() {
        return protocolType;
    }
}
