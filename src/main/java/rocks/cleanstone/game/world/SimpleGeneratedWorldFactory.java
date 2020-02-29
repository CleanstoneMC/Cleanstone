package rocks.cleanstone.game.world;

import org.springframework.stereotype.Component;
import rocks.cleanstone.game.entity.EntityRegistry;
import rocks.cleanstone.game.world.config.WorldConfig;
import rocks.cleanstone.game.world.generation.WorldGenerator;
import rocks.cleanstone.game.world.region.RegionManager;
import rocks.cleanstone.storage.world.WorldDataSource;

@Component
public class SimpleGeneratedWorldFactory implements WorldFactory {
    @Override
    public World get(WorldConfig worldConfig, WorldGenerator worldGenerator, WorldDataSource worldDataSource, RegionManager regionManager, EntityRegistry entityRegistry) {
        return new SimpleGeneratedWorld(worldConfig, worldGenerator, worldDataSource, regionManager, entityRegistry);
    }
}
