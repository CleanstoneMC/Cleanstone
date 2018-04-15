package rocks.cleanstone.net.packet.minecraft.send;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.SendPacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftSendPacketType;
import rocks.cleanstone.net.packet.minecraft.enums.Difficulty;

public class ServerDifficultyPacket extends SendPacket {

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
        return MinecraftSendPacketType.SERVER_DIFFICULTY;
    }
}
