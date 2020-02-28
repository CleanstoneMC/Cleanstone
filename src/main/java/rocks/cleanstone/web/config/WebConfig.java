package rocks.cleanstone.web.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import rocks.cleanstone.core.config.structs.NetworkConfig;

/**
 * (Currently unused) Section of the main configuration file with various cleanstone protocol-related
 * properties
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "web")
public class WebConfig extends NetworkConfig {
    private boolean enabled;
}
