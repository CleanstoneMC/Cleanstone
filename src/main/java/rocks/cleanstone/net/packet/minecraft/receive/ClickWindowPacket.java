package rocks.cleanstone.net.packet.minecraft.receive;

import rocks.cleanstone.net.packet.PacketDirection;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.StandardPacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftPacket;
import rocks.cleanstone.net.packet.minecraft.data.Slot;

public class ClickWindowPacket implements MinecraftPacket {

    private final int windowID;
    private final short slot;
    private final byte button;
    private final short actionNumber;
    private final int mode;
    private final Slot clickedItem;

    public ClickWindowPacket(int windowID, short slot, byte button, short actionNumber, int mode, Slot clickedItem) {
        this.windowID = windowID;
        this.slot = slot;
        this.button = button;
        this.actionNumber = actionNumber;
        this.mode = mode;
        this.clickedItem = clickedItem;
    }

    @Override
    public PacketType getType() {
        return StandardPacketType.MINECRAFT;
    }

    @Override
    public PacketDirection getDirection() {
        return PacketDirection.RECEIVE;
    }
}