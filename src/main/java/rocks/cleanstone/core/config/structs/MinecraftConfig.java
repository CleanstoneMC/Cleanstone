package rocks.cleanstone.core.config.structs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import rocks.cleanstone.core.CleanstoneServer;

import java.util.List;

/**
 * Section of the main configuration file with various game-related properties
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "minecraft")
public class MinecraftConfig {

    private Protocol bedrock;
    private Protocol java;

    private boolean onlineMode;
    private String motd;
    private int maxPlayers, maxViewDistance;
    private List<String> ops;
    private List<WorldConfig> worlds;

    public static MinecraftConfig getInstance() {
        return CleanstoneServer.getInstance().getMinecraftConfig();
    }

    public Protocol getBedrock() {
        return bedrock;
    }

    public void setBedrock(Protocol bedrock) {
        this.bedrock = bedrock;
    }

    public Protocol getJava() {
        return java;
    }

    public void setJava(Protocol java) {
        this.java = java;
    }

    public boolean isOnlineMode() {
        return onlineMode;
    }

    public void setOnlineMode(boolean onlineMode) {
        this.onlineMode = onlineMode;
    }

    public String getMotd() {
        return motd;
    }

    public void setMotd(String motd) {
        this.motd = motd;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public List<String> getOps() {
        return ops;
    }

    public void setOps(List<String> ops) {
        this.ops = ops;
    }

    public int getMaxViewDistance() {
        return maxViewDistance;
    }

    public void setMaxViewDistance(int maxViewDistance) {
        this.maxViewDistance = maxViewDistance;
    }

    public List<WorldConfig> getWorlds() {
        return worlds;
    }

    public void setWorlds(List<WorldConfig> worlds) {
        this.worlds = worlds;
    }
}

