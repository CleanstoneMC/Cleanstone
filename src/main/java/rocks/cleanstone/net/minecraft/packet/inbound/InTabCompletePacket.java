package rocks.cleanstone.net.packet.inbound;

import javax.annotation.Nullable;
import rocks.cleanstone.net.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.utils.Vector;

public class InTabCompletePacket implements Packet {
    private final int transactionId;
    private final String text;
    private final boolean assumeCommand;
    private final Vector lookedAtBlock;

    public InTabCompletePacket(int transactionId, String text, boolean assumeCommand, @Nullable Vector lookedAtBlock) {
        this.transactionId = transactionId;
        this.text = text;
        this.assumeCommand = assumeCommand;
        this.lookedAtBlock = lookedAtBlock;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public String getText() {
        return text;
    }

    public boolean isAssumeCommand() {
        return assumeCommand;
    }

    @Nullable
    public Vector getLookedAtBlock() {
        return lookedAtBlock;
    }

    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.TAB_COMPLETE;
    }
}
