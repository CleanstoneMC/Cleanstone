package rocks.cleanstone.endpoint.cleanstone.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import rocks.cleanstone.net.config.NetworkConfig;

import java.net.InetAddress;
import java.util.Set;

/**
 * (Currently unused) Section of the main configuration file with various cleanstone protocol-related
 * properties
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Configuration
@ConfigurationProperties(prefix = "cleanstone")
public class CleanstoneConfig extends NetworkConfig {
    private int mainServerPort;
    private InetAddress mainServerAddress;

    private Set<String> requiredSubServers;
}
