package rocks.cleanstone.net.mcpe.packet;

import rocks.cleanstone.net.mcpe.packet.inbound.*;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketDirection;
import rocks.cleanstone.net.packet.PacketType;

import javax.annotation.Nullable;

public enum MCPEInboundPacketType implements PacketType {
    LOGIN(LoginPacket.class),
    CLIENT_TO_SERVER_HANDSHAKE(ClientToServerHandshakePacket.class),
    RESOURCE_PACK_CLIENT_RESPONSE(ResourcePackClientResponsePacket.class),
    TEXT(TextPacket.class),
    MOVE_ENTITY(MoveEntityPacket.class),
    MOVE_PLAYER(MovePlayerPacket.class),
    RIDER_JUMP(RiderJumpPacket.class),
    LEVEL_SOUND_EVENT(LevelSoundEventPacket.class),
    ENTITY_EVENT(EntityEventPacket.class),
    INVENTORY_TRANSACTION(InventoryTransactionPacket.class),
    MOB_EQUIPMENT(MobEquipmentPacket.class),
    MOB_ARMOR_EQUIPMENT(MobArmorEquipmentPacket.class),
    INTERACT(InteractPacket.class),
    BLOCK_PICK_REQUEST(BlockPickRequestPacket.class),
    ENTITY_PICK_REQUEST(EntityPickRequestPacket.class),
    PLAYER_ACTION(PlayerActionPacket.class),
    ENTITY_FALL(EntityFallPacket.class),
    SET_ENTITY_DATA(SetEntityDataPacket.class),
    SET_ENTITY_MOTION(SetEntityMotionPacket.class),
    ANIMATE(AnimatePacket.class),
    RESPAWN(RespawnPacket.class),
    CONTAINER_CLOSE(ContainerClosePacket.class),
    PLAYER_HOTBAR(PlayerHotbarPacket.class),
    INVENTORY_CONTENT(InventoryContentPacket.class),
    INVENTORY_SLOT(InventorySlotPacket.class),
    CRAFTING_EVENT(CraftingEventPacket.class),
    ADVENTURE_SETTINGS(AdventureSettingsPacket.class),
    BLOCK_ENTITY_DATA(BlockEntityDataPacket.class),
    PLAYER_INPUT(PlayerInputPacket.class),
    SET_PLAYER_GAME_TYPE(SetPlayerGameTypePacket.class),
    MAP_INFO_REQUEST(MapInfoRequestPacket.class),
    REQUEST_CHUNK_RADIUS(RequestChunkRadiusPacket.class),
    ITEM_FRAME_DROP_ITEM(ItemFrameDropItemPacket.class),
    COMMAND_REQUEST(CommandRequestPacket.class),
    COMMAND_BLOCK_UPDATE(CommandBlockUpdatePacket.class),
    RESOURCE_PACK_CHUNK_REQUEST(ResourcePackChunkRequestPacket.class),
    PURCHASE_RECEIPT(PurchaseReceiptPacket.class),
    PLAYER_SKIN(PlayerSkinPacket.class),
    NPC_REQUEST(NpcRequestPacket.class),
    PHOTO_TRANSFER(PhotoTransferPacket.class),
    MODAL_FORM_RESPONSE(ModalFormResponsePacket.class),
    SERVER_SETTINGS_REQUEST(ServerSettingsRequestPacket.class),
    LAB_TABLE(LabTablePacket.class),
    SET_LOCAL_PLAYER_AS_INITIALIZED_PACKET(SetLocalPlayerAsInitializedPacketPacket.class);

    private final Class<? extends Packet> packetClass;

    MCPEInboundPacketType(Class<? extends Packet> packetClass) {
        this.packetClass = packetClass;
    }

    @Nullable
    public static MCPEInboundPacketType byPacketClass(Class<? extends Packet> packetClass) {
        for (MCPEInboundPacketType type : values()) {
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
