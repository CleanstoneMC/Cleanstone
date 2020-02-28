package rocks.cleanstone.endpoint.minecraft.vanilla.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import rocks.cleanstone.core.config.structs.NetworkConfig;

import java.util.List;

/**
 * Section of the main configuration file with various minecraft protocol-related
 * properties
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Configuration
@ConfigurationProperties(prefix = "endpoint.vanilla")
public class MinecraftConfig extends NetworkConfig {
    private boolean enabled;
    private List<String> versions;
}
