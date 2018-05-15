package rocks.cleanstone.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import rocks.cleanstone.core.CleanstoneServer;

import java.net.InetAddress;
import java.util.List;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "minecraft")
public class MinecraftConfig {

    private int port;
    private InetAddress address;
    private boolean onlineMode;
    private String motd;
    private int maxPlayers;
    private List<String> autoLoadWorlds;
    private List<String> ops;

    public static MinecraftConfig getInstance() {
        return CleanstoneServer.getInstance().getMinecraftConfig();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
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

    public List<String> getAutoLoadWorlds() {
        return autoLoadWorlds;
    }

    public void setAutoLoadWorlds(List<String> autoLoadWorlds) {
        this.autoLoadWorlds = autoLoadWorlds;
    }

    @Override
    public String toString() {
        return "MinecraftConfig{" +
                "port=" + port +
                ", address=" + address +
                ", onlineMode=" + onlineMode + "}";
    }

    public List<String> getOps() {
        return ops;
    }

    public void setOps(List<String> ops) {
        this.ops = ops;
    }
}
