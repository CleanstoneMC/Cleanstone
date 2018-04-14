package rocks.cleanstone.net.packet.minecraft.receive;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.ReceivePacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftReceivePacketType;
import rocks.cleanstone.net.utils.Vector;

public class TabCompletePacket extends ReceivePacket {

    private final String text;
    private final boolean assumeCommand;
    private final boolean hasPosition;
    private final Vector lookedAtBlock;


    public TabCompletePacket(String text, boolean assumeCommand, boolean hasPosition, Vector lookedAtBlock) {
        this.text = text;
        this.assumeCommand = assumeCommand;
        this.hasPosition = hasPosition;
        this.lookedAtBlock = lookedAtBlock;
    }

    public String getText() {
        return text;
    }

    public boolean isAssumeCommand() {
        return assumeCommand;
    }

    public boolean isHasPosition() {
        return hasPosition;
    }

    public Vector getLookedAtBlock() {
        return lookedAtBlock;
    }

    @Override
    public PacketType getType() {
        return MinecraftReceivePacketType.TAB_COMPLETE;
    }
}
