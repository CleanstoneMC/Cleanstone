package rocks.cleanstone.endpoint.minecraft.bedrock.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import rocks.cleanstone.core.config.structs.NetworkConfig;

/**
 * Section of the main configuration file with various bedrock protocol-related
 * properties
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Configuration
@ConfigurationProperties(prefix = "endpoint.bedrock")
public class BedrockConfig extends NetworkConfig {
    private boolean enabled;
}
