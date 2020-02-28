package rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.game.inventory.item.ItemStack;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

import java.util.List;

public class WindowItemsPacket implements Packet {

    private final byte windowID;
    private final List<ItemStack> itemData;

    public WindowItemsPacket(byte windowID, List<ItemStack> itemDataList) {
        this.windowID = windowID;
        this.itemData = itemDataList;
    }

    public byte getWindowID() {
        return windowID;
    }

    public List<ItemStack> getSlots() {
        return itemData;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.WINDOW_ITEMS;
    }
}
