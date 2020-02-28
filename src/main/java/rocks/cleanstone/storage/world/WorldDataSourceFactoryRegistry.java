package rocks.cleanstone.storage.world;

import java.util.List;

public interface WorldDataSourceFactoryRegistry {

    List<WorldDataSourceFactory> getAllWorldDataSourceFactories();

    WorldDataSourceFactory getWorldDataSourceFactory();
}
