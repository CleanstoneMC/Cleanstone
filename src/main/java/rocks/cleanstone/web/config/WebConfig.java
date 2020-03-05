package rocks.cleanstone.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import rocks.cleanstone.net.config.NetworkConfig;

/**
 * (Currently unused) Section of the main configuration file with various cleanstone protocol-related
 * properties
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "web")
public class WebConfig {
    private NetworkConfig server;
    private SpringBootAdminInstanceConfig client;
    private SpringBootAdminInstanceConfig admin;
}

