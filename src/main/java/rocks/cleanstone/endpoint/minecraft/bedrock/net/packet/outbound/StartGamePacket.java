package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockOutboundPacketType;
import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.data.GameRules;
import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.utils.Vector;
import rocks.cleanstone.utils.Vector2D;

import java.util.List;

public class StartGamePacket implements Packet {

    private final long entityIDSelf;
    private final long runtimeEntityID;
    private final int playerGamemode;
    private final Vector spawn;
    private final Vector2D unknown1;
    private final int seed;
    private final int dimension;
    private final int generator;
    private final int gamemode;
    private final int difficulty;
    private final int x;
    private final int y;
    private final int z;
    private final boolean hasAchievementsDisabled;
    private final int dayCycleStopTime;
    private final boolean eDUMode;
    private final boolean hasEDUFeaturesEnabled;
    private final float rainLevel;
    private final float lightningLevel;
    private final boolean isMultiplayer;
    private final boolean broadcastToLAN;
    private final boolean broadcastToXBL;
    private final boolean enableCommands;
    private final boolean isTexturepacksRequired;
    private final GameRules gameRules;
    private final boolean bonusChest;
    private final boolean mapEnabled;
    private final boolean trustPlayers;
    private final int permissionLevel;
    private final int gamePublishSetting;
    private final int serverChunkTickRange;
    private final boolean hasPlatformBroadcast;
    private final int platformBroadcastMode;
    private final boolean xboxLiveBroadcastIntent;
    private final boolean hasLockedBehaviorPack;
    private final boolean hasLockedResourcePack;
    private final boolean isFromLockedWorldTemplate;
    private final String levelID;
    private final String worldName;
    private final String premiumWorldTemplateId;
    private final boolean isTrial;
    private final long currentTick;
    private final int enchantmentSeed;
    private final List<BlockState> blockstates;
    private final String multiplayerCorrelationID;

    public StartGamePacket(long entityIDSelf, long runtimeEntityID, int playerGamemode, Vector spawn, Vector2D unknown1, int seed, int dimension, int generator, int gamemode, int difficulty, int x, int y, int z, boolean hasAchievementsDisabled, int dayCycleStopTime, boolean eDUMode, boolean hasEDUFeaturesEnabled, float rainLevel, float lightningLevel, boolean isMultiplayer, boolean broadcastToLAN, boolean broadcastToXBL, boolean enableCommands, boolean isTexturepacksRequired, GameRules gameRules, boolean bonusChest, boolean mapEnabled, boolean trustPlayers, int permissionLevel, int gamePublishSetting, int serverChunkTickRange, boolean hasPlatformBroadcast, int platformBroadcastMode, boolean xboxLiveBroadcastIntent, boolean hasLockedBehaviorPack, boolean hasLockedResourcePack, boolean isFromLockedWorldTemplate, String levelID, String worldName, String premiumWorldTemplateId, boolean isTrial, long currentTick, int enchantmentSeed, List<BlockState> blockstates, String multiplayerCorrelationID) {
        this.entityIDSelf = entityIDSelf;
        this.runtimeEntityID = runtimeEntityID;
        this.playerGamemode = playerGamemode;
        this.spawn = spawn;
        this.unknown1 = unknown1;
        this.seed = seed;
        this.dimension = dimension;
        this.generator = generator;
        this.gamemode = gamemode;
        this.difficulty = difficulty;
        this.x = x;
        this.y = y;
        this.z = z;
        this.hasAchievementsDisabled = hasAchievementsDisabled;
        this.dayCycleStopTime = dayCycleStopTime;
        this.eDUMode = eDUMode;
        this.hasEDUFeaturesEnabled = hasEDUFeaturesEnabled;
        this.rainLevel = rainLevel;
        this.lightningLevel = lightningLevel;
        this.isMultiplayer = isMultiplayer;
        this.broadcastToLAN = broadcastToLAN;
        this.broadcastToXBL = broadcastToXBL;
        this.enableCommands = enableCommands;
        this.isTexturepacksRequired = isTexturepacksRequired;
        this.gameRules = gameRules;
        this.bonusChest = bonusChest;
        this.mapEnabled = mapEnabled;
        this.trustPlayers = trustPlayers;
        this.permissionLevel = permissionLevel;
        this.gamePublishSetting = gamePublishSetting;
        this.serverChunkTickRange = serverChunkTickRange;
        this.hasPlatformBroadcast = hasPlatformBroadcast;
        this.platformBroadcastMode = platformBroadcastMode;
        this.xboxLiveBroadcastIntent = xboxLiveBroadcastIntent;
        this.hasLockedBehaviorPack = hasLockedBehaviorPack;
        this.hasLockedResourcePack = hasLockedResourcePack;
        this.isFromLockedWorldTemplate = isFromLockedWorldTemplate;
        this.levelID = levelID;
        this.worldName = worldName;
        this.premiumWorldTemplateId = premiumWorldTemplateId;
        this.isTrial = isTrial;
        this.currentTick = currentTick;
        this.enchantmentSeed = enchantmentSeed;
        this.blockstates = blockstates;
        this.multiplayerCorrelationID = multiplayerCorrelationID;
    }

    public long getEntityIDSelf() {
        return entityIDSelf;
    }

    public long getRuntimeEntityID() {
        return runtimeEntityID;
    }

    public int getPlayerGamemode() {
        return playerGamemode;
    }

    public Vector getSpawn() {
        return spawn;
    }

    public Vector2D getUnknown1() {
        return unknown1;
    }

    public int getSeed() {
        return seed;
    }

    public int getDimension() {
        return dimension;
    }

    public int getGenerator() {
        return generator;
    }

    public int getGamemode() {
        return gamemode;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public boolean getHasAchievementsDisabled() {
        return hasAchievementsDisabled;
    }

    public int getDayCycleStopTime() {
        return dayCycleStopTime;
    }

    public boolean getEDUMode() {
        return eDUMode;
    }

    public boolean getHasEDUFeaturesEnabled() {
        return hasEDUFeaturesEnabled;
    }

    public float getRainLevel() {
        return rainLevel;
    }

    public float getLightningLevel() {
        return lightningLevel;
    }

    public boolean getIsMultiplayer() {
        return isMultiplayer;
    }

    public boolean getBroadcastToLAN() {
        return broadcastToLAN;
    }

    public boolean getBroadcastToXBL() {
        return broadcastToXBL;
    }

    public boolean getEnableCommands() {
        return enableCommands;
    }

    public boolean getIsTexturepacksRequired() {
        return isTexturepacksRequired;
    }

    public GameRules getGameRules() {
        return gameRules;
    }

    public boolean getBonusChest() {
        return bonusChest;
    }

    public boolean getMapEnabled() {
        return mapEnabled;
    }

    public boolean getTrustPlayers() {
        return trustPlayers;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public int getGamePublishSetting() {
        return gamePublishSetting;
    }

    public int getServerChunkTickRange() {
        return serverChunkTickRange;
    }

    public boolean getHasPlatformBroadcast() {
        return hasPlatformBroadcast;
    }

    public int getPlatformBroadcastMode() {
        return platformBroadcastMode;
    }

    public boolean getXboxLiveBroadcastIntent() {
        return xboxLiveBroadcastIntent;
    }

    public boolean getHasLockedBehaviorPack() {
        return hasLockedBehaviorPack;
    }

    public boolean getHasLockedResourcePack() {
        return hasLockedResourcePack;
    }

    public boolean getIsFromLockedWorldTemplate() {
        return isFromLockedWorldTemplate;
    }

    public String getLevelID() {
        return levelID;
    }

    public String getWorldName() {
        return worldName;
    }

    public String getPremiumWorldTemplateId() {
        return premiumWorldTemplateId;
    }

    public boolean getIsTrial() {
        return isTrial;
    }

    public long getCurrentTick() {
        return currentTick;
    }

    public int getEnchantmentSeed() {
        return enchantmentSeed;
    }

    public List<BlockState> getBlockstates() {
        return blockstates;
    }

    public String getMultiplayerCorrelationID() {
        return multiplayerCorrelationID;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.START_GAME;
    }
}

