package rocks.cleanstone.net.packet.inbound;

import rocks.cleanstone.net.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ConfirmTransactionPacket implements Packet {

    private final byte windowID;
    private final short actionNumber;
    private final boolean accepted;

    public ConfirmTransactionPacket(byte windowID, short actionNumber, boolean accepted) {
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
