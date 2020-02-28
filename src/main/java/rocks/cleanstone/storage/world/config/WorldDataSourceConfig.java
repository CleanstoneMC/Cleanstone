package rocks.cleanstone.storage.world.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Section of the main configuration file with world storage configuration properties
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "storage.world")
public class WorldDataSourceConfig {
    String engine;
}
