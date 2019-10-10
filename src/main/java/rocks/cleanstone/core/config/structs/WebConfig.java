package rocks.cleanstone.core.config.structs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

/**
 * (Currently unused) Section of the main configuration file with various cleanstone protocol-related
 * properties
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "web")
public class WebConfig {

    private boolean enabled;

    private int port;
    private InetAddress address;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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
        return "WebConfig{" +
                "enabled=" + enabled +
                ", port=" + port +
                ", address=" + address +
                '}';
    }
}
