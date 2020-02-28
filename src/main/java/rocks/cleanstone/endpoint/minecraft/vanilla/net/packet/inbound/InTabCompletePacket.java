package rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.inbound;

import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.utils.Vector;

public class InTabCompletePacket implements Packet {

    private final String text;

    // v1.13+
    private int transactionId;

    // v1.12-
    private boolean assumeCommand;
    private boolean hasPosition;
    private Vector lookedAtBlock;


    public InTabCompletePacket(String text, boolean assumeCommand, boolean hasPosition, Vector lookedAtBlock) {
        this.text = text;
        this.assumeCommand = assumeCommand;
        this.hasPosition = hasPosition;
        this.lookedAtBlock = lookedAtBlock;
    }

    public InTabCompletePacket(String text, int transactionId) {
        this.text = text;
        this.transactionId = transactionId;
    }

    public String getText() {
        return text;
    }

    public int getTransactionId() {
        return transactionId;
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
        return MinecraftInboundPacketType.TAB_COMPLETE;
    }
}
