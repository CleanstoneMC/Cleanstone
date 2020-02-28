package rocks.cleanstone.storage.world;

import org.springframework.stereotype.Component;
import rocks.cleanstone.storage.world.config.WorldDataSourceConfig;

import java.util.List;

@Component
public class SimpleWorldDataSourceFactoryRegistry implements WorldDataSourceFactoryRegistry {

    private final WorldDataSourceConfig worldDataSourceConfig;
    private final List<WorldDataSourceFactory> worldDataSourceFactories;

    public SimpleWorldDataSourceFactoryRegistry(WorldDataSourceConfig worldDataSourceConfig, List<WorldDataSourceFactory> worldDataSourceFactories) {
        this.worldDataSourceConfig = worldDataSourceConfig;
        this.worldDataSourceFactories = worldDataSourceFactories;
    }

    @Override
    public List<WorldDataSourceFactory> getAllWorldDataSourceFactories() {
        return worldDataSourceFactories;
    }

    @Override
    public WorldDataSourceFactory getWorldDataSourceFactory() {
        return worldDataSourceFactories.stream().filter(i -> i.getName().equals(worldDataSourceConfig.getEngine())).findFirst().orElseThrow();
    }
}
