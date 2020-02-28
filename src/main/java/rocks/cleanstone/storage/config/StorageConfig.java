package rocks.cleanstone.storage.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Section of the main configuration file with storage configuration properties
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "storage")
public class StorageConfig {
}
