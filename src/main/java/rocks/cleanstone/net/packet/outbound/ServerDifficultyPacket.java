package rocks.cleanstone.net.packet.outbound;

import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.enums.Difficulty;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ServerDifficultyPacket implements Packet {

    private final Difficulty difficulty;

    public ServerDifficultyPacket(byte difficulty) {
        this.difficulty = Difficulty.fromDifficultID(difficulty);
    }

    public ServerDifficultyPacket(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.SERVER_DIFFICULTY;
    }
}
