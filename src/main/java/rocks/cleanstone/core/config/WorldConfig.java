package rocks.cleanstone.core.config;

/**
 * Section of the {@link MinecraftConfig} with various world-related properties
 */
public class WorldConfig {
    private String name;
    private String generator;
    private boolean autoload;
    private boolean firstSpawnWorld;
    private int seed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public boolean isAutoload() {
        return autoload;
    }

    public void setAutoload(boolean autoload) {
        this.autoload = autoload;
    }

    public boolean isFirstSpawnWorld() {
        return firstSpawnWorld;
    }

    public void setFirstSpawnWorld(boolean firstSpawnWorld) {
        this.firstSpawnWorld = firstSpawnWorld;
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }
}
