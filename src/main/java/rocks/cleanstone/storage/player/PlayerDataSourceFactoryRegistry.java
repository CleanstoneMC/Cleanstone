package rocks.cleanstone.storage.player;

import java.util.List;

public interface PlayerDataSourceFactoryRegistry {

    List<PlayerDataSourceFactory> getAllPlayerDataSourceFactories();

    PlayerDataSourceFactory getPlayerDataSourceFactory();
}
