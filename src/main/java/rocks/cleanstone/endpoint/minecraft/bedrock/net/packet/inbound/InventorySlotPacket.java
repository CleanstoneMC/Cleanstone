package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.inbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockInboundPacketType;
import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.data.Item;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class InventorySlotPacket implements Packet {

    private final int inventoryId;
    private final int slot;
    private final Item item;

    public InventorySlotPacket(int inventoryId, int slot, Item item) {
        this.inventoryId = inventoryId;
        this.slot = slot;
        this.item = item;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public int getSlot() {
        return slot;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public PacketType getType() {
        return BedrockInboundPacketType.INVENTORY_SLOT;
    }
}

