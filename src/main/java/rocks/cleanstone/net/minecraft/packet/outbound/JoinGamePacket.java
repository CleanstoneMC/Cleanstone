package rocks.cleanstone.net.minecraft.packet.outbound;

import rocks.cleanstone.net.minecraft.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.minecraft.packet.enums.Difficulty;
import rocks.cleanstone.net.minecraft.packet.enums.Dimension;
import rocks.cleanstone.net.minecraft.packet.enums.LevelType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class JoinGamePacket implements Packet {

    private final int entityID;
    private final int gamemode;
    private final Dimension dimesion;
    private final Difficulty difficulty;
    private final byte maxPlayers;
    private final LevelType levelType;
    private final boolean reduceDebugInfo;

    public JoinGamePacket(int entityID, int gamemode, Dimension dimesion, Difficulty difficulty, byte maxPlayers, LevelType levelType, boolean reduceDebugInfo) {
        this.entityID = entityID;
        this.gamemode = gamemode;
        this.dimesion = dimesion;
        this.difficulty = difficulty;
        this.maxPlayers = maxPlayers;
        this.levelType = levelType;
        this.reduceDebugInfo = reduceDebugInfo;
    }

    public JoinGamePacket(int entityID, int gamemode, Dimension dimesion, Difficulty difficulty, LevelType levelType, boolean reduceDebugInfo) {
        this.entityID = entityID;
        this.gamemode = gamemode;
        this.dimesion = dimesion;
        this.difficulty = difficulty;
        this.maxPlayers = 0;
        this.levelType = levelType;
        this.reduceDebugInfo = reduceDebugInfo;
    }

    public int getEntityID() {
        return entityID;
    }

    public int getGamemode() {
        return gamemode;
    }

    public Dimension getDimesion() {
        return dimesion;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public byte getMaxPlayers() {
        return maxPlayers;
    }

    public LevelType getLevelType() {
        return levelType;
    }

    public boolean isReduceDebugInfo() {
        return reduceDebugInfo;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.JOIN_GAME;
    }
}
