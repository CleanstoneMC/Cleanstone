package rocks.cleanstone.net.mcpe.packet.inbound;

import rocks.cleanstone.game.inventory.item.ItemStack;
import rocks.cleanstone.net.mcpe.packet.MCPEInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class InventoryContentPacket implements Packet {

    private final int inventoryId;
    private final ItemStack input;

    public InventoryContentPacket(int inventoryId, ItemStack input) {
        this.inventoryId = inventoryId;
        this.input = input;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public ItemStack getInput() {
        return input;
    }

    @Override
    public PacketType getType() {
        return MCPEInboundPacketType.INVENTORY_CONTENT;
    }
}

