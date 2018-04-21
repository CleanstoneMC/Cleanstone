package rocks.cleanstone.net.packet.minecraft.outbound;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.minecraft.enums.Difficulty;

public class ServerDifficultyPacket {

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
