package rocks.cleanstone.storage.player.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Section of the main configuration file with player storage configuration properties
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "storage.player")
public class PlayerDataSourceConfig {
    String engine;
}
