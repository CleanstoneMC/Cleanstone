package rocks.cleanstone.net.packet.outbound;

import java.util.List;

import rocks.cleanstone.game.inventory.item.ItemStack;
import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

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
