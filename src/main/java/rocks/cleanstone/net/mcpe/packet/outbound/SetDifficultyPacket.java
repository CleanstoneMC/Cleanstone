package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class SetDifficultyPacket implements Packet {

    private final int difficulty;

    public SetDifficultyPacket(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getDifficulty() {
        return difficulty;
    }

    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.SET_DIFFICULTY;
    }
}

