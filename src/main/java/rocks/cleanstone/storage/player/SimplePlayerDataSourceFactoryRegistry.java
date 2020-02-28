package rocks.cleanstone.storage.player;

import org.springframework.stereotype.Component;
import rocks.cleanstone.storage.player.config.PlayerDataSourceConfig;

import java.util.List;

@Component
public class SimplePlayerDataSourceFactoryRegistry implements PlayerDataSourceFactoryRegistry {

    private final PlayerDataSourceConfig worldDataSourceConfig;
    private final List<PlayerDataSourceFactory> worldDataSourceFactories;

    public SimplePlayerDataSourceFactoryRegistry(PlayerDataSourceConfig worldDataSourceConfig, List<PlayerDataSourceFactory> worldDataSourceFactories) {
        this.worldDataSourceConfig = worldDataSourceConfig;
        this.worldDataSourceFactories = worldDataSourceFactories;
    }

    @Override
    public List<PlayerDataSourceFactory> getAllPlayerDataSourceFactories() {
        return worldDataSourceFactories;
    }

    @Override
    public PlayerDataSourceFactory getPlayerDataSourceFactory() {
        return worldDataSourceFactories.stream().filter(i -> i.getName().equals(worldDataSourceConfig.getEngine())).findFirst().orElseThrow();
    }
}
