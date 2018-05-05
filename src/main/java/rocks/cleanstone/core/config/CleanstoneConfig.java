package rocks.cleanstone.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.util.Set;

import rocks.cleanstone.core.CleanstoneServer;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "cleanstone")
public class CleanstoneConfig {

    private boolean subServer;

    private int mainServerPort;
    private InetAddress mainServerAddress;

    private Set<String> requiredSubServers;

    private int port;
    private InetAddress address;

    public static CleanstoneConfig getInstance() {
        return CleanstoneServer.getInstance().getCleanstoneConfig();
    }

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

    public InetAddress getMainServerAddress() {
        return mainServerAddress;
    }

    public void setMainServerAddress(InetAddress mainServerAddress) {
        this.mainServerAddress = mainServerAddress;
    }

    public Set<String> getRequiredSubServers() {
        return requiredSubServers;
    }

    public void setRequiredSubServers(Set<String> requiredSubServers) {
        this.requiredSubServers = requiredSubServers;
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

    @Override
    public String toString() {
        return "CleanstoneConfig{" +
                "subServer=" + subServer +
                ", mainServerPort=" + mainServerPort +
                ", mainServerAddress=" + mainServerAddress +
                ", requiredSubServers=" + requiredSubServers +
                ", port=" + port +
                ", address=" + address +
                '}';
    }
}
