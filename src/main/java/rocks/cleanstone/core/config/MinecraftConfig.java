package rocks.cleanstone.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import rocks.cleanstone.core.CleanstoneApplication;

import java.net.InetAddress;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "minecraft")
public class MinecraftConfig {

    private int port;
    private InetAddress address;

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

    public static MinecraftConfig getInstance() {
        return CleanstoneApplication.getApplication().getMinecraftConfig();
    }

    @Override
    public String toString() {
        return "MinecraftConfig{" +
                "port=" + port +
                ", address=" + address +
                '}';
    }
}
