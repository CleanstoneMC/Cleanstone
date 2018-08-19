package rocks.cleanstone.net.packet.outbound;

import java.util.List;

import rocks.cleanstone.game.command.completion.CompletionMatch;
import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class OutTabCompletePacket implements Packet {

    private final int transactionId;
    private final int start;
    private final int length;
    private final List<CompletionMatch> matches;

    public OutTabCompletePacket(int transactionId, int start, int length, List<CompletionMatch> matches) {
        this.transactionId = transactionId;
        this.start = start;
        this.length = length;
        this.matches = matches;
    }

    public List<CompletionMatch> getMatches() {
        return matches;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.TAB_COMPLETE;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getStart() {
        return start;
    }

    public int getLength() {
        return length;
    }
}
