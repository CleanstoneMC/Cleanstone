package rocks.cleanstone.net.bedrock.packet.inbound;

import rocks.cleanstone.net.bedrock.packet.BedrockInboundPacketType;
import rocks.cleanstone.net.bedrock.packet.data.Item;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class MobEquipmentPacket implements Packet {

    private final long runtimeEntityID;
    private final Item item;
    private final byte slot;
    private final byte selectedSlot;
    private final byte windowsId;

    public MobEquipmentPacket(long runtimeEntityID, Item item, byte slot, byte selectedSlot, byte windowsId) {
        this.runtimeEntityID = runtimeEntityID;
        this.item = item;
        this.slot = slot;
        this.selectedSlot = selectedSlot;
        this.windowsId = windowsId;
    }

    public long getRuntimeEntityID() {
        return runtimeEntityID;
    }

    public Item getItem() {
        return item;
    }

    public byte getSlot() {
        return slot;
    }

    public byte getSelectedSlot() {
        return selectedSlot;
    }

    public byte getWindowsId() {
        return windowsId;
    }

    @Override
    public PacketType getType() {
        return BedrockInboundPacketType.MOB_EQUIPMENT;
    }
}

