package rocks.cleanstone.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.util.List;

import rocks.cleanstone.core.CleanstoneServer;

/**
 * Section of the main configuration file with various game-related properties
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "minecraft")
public class MinecraftConfig {

    private int port, mcpePort;
    private InetAddress address, mcpeAddress;
    private boolean onlineMode;
    private String motd;
    private int maxPlayers, maxViewDistance;
    private List<String> ops;
    private List<WorldConfig> worlds;

    public static MinecraftConfig getInstance() {
        return CleanstoneServer.getInstance().getMinecraftConfig();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getMcpePort() {
        return mcpePort;
    }

    public void setMcpePort(int mcpePort) {
        this.mcpePort = mcpePort;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public InetAddress getMcpeAddress() {
        return mcpeAddress;
    }

    public void setMcpeAddress(InetAddress mcpeAddress) {
        this.mcpeAddress = mcpeAddress;
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

