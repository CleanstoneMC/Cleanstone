package rocks.cleanstone.core.config.structs;

import lombok.Data;

/**
 * Section of the {@link GameConfig} with various world-related properties
 */
@Data
public class WorldConfig {
    private String name;
    private String generator;
    private boolean autoload;
    private boolean firstSpawnWorld;
    private int seed;
}
