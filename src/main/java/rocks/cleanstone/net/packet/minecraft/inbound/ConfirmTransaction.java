package rocks.cleanstone.net.packet.minecraft.inbound;

import rocks.cleanstone.net.packet.InboundPacket;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftInboundPacketType;

public class ConfirmTransaction extends InboundPacket {

    private final byte windowID;
    private final short actionNumber;
    private final boolean accepted;

    public ConfirmTransaction(byte windowID, short actionNumber, boolean accepted) {
        this.windowID = windowID;
        this.actionNumber = actionNumber;
        this.accepted = accepted;
    }

    public byte getWindowID() {
        return windowID;
    }

    public short getActionNumber() {
        return actionNumber;
    }

    public boolean isAccepted() {
        return accepted;
    }

    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.CONFIRM_TRANSACTION;
    }
}
