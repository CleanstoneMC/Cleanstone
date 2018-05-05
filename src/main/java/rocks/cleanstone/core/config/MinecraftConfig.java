package rocks.cleanstone.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

import rocks.cleanstone.core.CleanstoneServer;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "minecraft")
public class MinecraftConfig {

    private int port;
    private InetAddress address;

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

    @Override
    public String toString() {
        return "MinecraftConfig{" +
                "port=" + port +
                ", address=" + address +
                '}';
    }
}
