package rocks.cleanstone.core;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "cleanstone")
public class CleanstoneConfig {

    private boolean subServer;

    private int mainServerPort;
    private String mainServerAddress;

    private Set<String> requiredSubServers;

    private int minecraftPort;
    private String minecraftBindAddress;

    private int cleanstonePort;
    private String cleanstoneBindAddress;

    public boolean isSubServer() {
        return subServer;
    }

    public void setSubServer(boolean subServer) {
        this.subServer = subServer;
    }

    public int getMainServerPort() {
        return mainServerPort;
    }

    public void setMainServerPort(int mainServerPort) {
        this.mainServerPort = mainServerPort;
    }

    public String getMainServerAddress() {
        return mainServerAddress;
    }

    public void setMainServerAddress(String mainServerAddress) {
        this.mainServerAddress = mainServerAddress;
    }

    public Set<String> getRequiredSubServers() {
        return requiredSubServers;
    }

    public void setRequiredSubServers(Set<String> requiredSubServers) {
        this.requiredSubServers = requiredSubServers;
    }

    public int getMinecraftPort() {
        return minecraftPort;
    }

    public void setMinecraftPort(int minecraftPort) {
        this.minecraftPort = minecraftPort;
    }

    public String getMinecraftBindAddress() {
        return minecraftBindAddress;
    }

    public void setMinecraftBindAddress(String minecraftBindAddress) {
        this.minecraftBindAddress = minecraftBindAddress;
    }

    public int getCleanstonePort() {
        return cleanstonePort;
    }

    public void setCleanstonePort(int cleanstonePort) {
        this.cleanstonePort = cleanstonePort;
    }

    public String getCleanstoneBindAddress() {
        return cleanstoneBindAddress;
    }

    public void setCleanstoneBindAddress(String cleanstoneBindAddress) {
        this.cleanstoneBindAddress = cleanstoneBindAddress;
    }

    @Override
    public String toString() {
        return "CleanstoneConfig{" +
                "subServer=" + subServer +
                ", mainServerPort=" + mainServerPort +
                ", mainServerAddress='" + mainServerAddress + '\'' +
                ", requiredSubServers=" + requiredSubServers +
                ", minecraftPort=" + minecraftPort +
                ", minecraftBindAddress='" + minecraftBindAddress + '\'' +
                ", cleanstonePort=" + cleanstonePort +
                ", cleanstoneBindAddress='" + cleanstoneBindAddress + '\'' +
                '}';
    }
}
