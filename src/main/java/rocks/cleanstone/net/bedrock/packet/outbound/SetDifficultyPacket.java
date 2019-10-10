package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
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
        return BedrockOutboundPacketType.SET_DIFFICULTY;
    }
}

