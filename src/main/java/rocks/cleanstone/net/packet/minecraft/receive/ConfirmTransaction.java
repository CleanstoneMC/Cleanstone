package rocks.cleanstone.net.packet.minecraft.receive;

import rocks.cleanstone.net.packet.PacketDirection;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.StandardPacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftPacket;

public class ConfirmTransaction implements MinecraftPacket {

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
        return StandardPacketType.MINECRAFT;
    }

    @Override
    public PacketDirection getDirection() {
        return PacketDirection.RECEIVE;
    }
}
