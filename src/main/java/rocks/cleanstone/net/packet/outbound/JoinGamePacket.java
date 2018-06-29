package rocks.cleanstone.net.packet.outbound;

import rocks.cleanstone.game.gamemode.vanilla.VanillaGameMode;
import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.enums.Difficulty;
import rocks.cleanstone.net.packet.enums.Dimension;
import rocks.cleanstone.net.packet.enums.LevelType;

public class JoinGamePacket implements Packet {

    private final int entityID;
    private final VanillaGameMode gamemode;
    private final Dimension dimension;
    private final Difficulty difficulty;
    private final byte maxPlayers;
    private final LevelType levelType;
    private final boolean reduceDebugInfo;

    public JoinGamePacket(int entityID, VanillaGameMode gamemode, Dimension dimension, Difficulty difficulty,
                          LevelType levelType, boolean reduceDebugInfo) {
        this(entityID, gamemode, dimension, difficulty, (byte) 0, levelType, reduceDebugInfo);
    }

    public JoinGamePacket(int entityID, VanillaGameMode gamemode, Dimension dimension, Difficulty difficulty,
                          byte maxPlayers, LevelType levelType, boolean reduceDebugInfo) {
        this.entityID = entityID;
        this.gamemode = gamemode;
        this.dimension = dimension;
        this.difficulty = difficulty;
        this.maxPlayers = maxPlayers;
        this.levelType = levelType;
        this.reduceDebugInfo = reduceDebugInfo;
    }

    public int getEntityID() {
        return entityID;
    }

    public VanillaGameMode getGamemode() {
        return gamemode;
    }

    public Dimension getDimension() {
        return dimension;
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
