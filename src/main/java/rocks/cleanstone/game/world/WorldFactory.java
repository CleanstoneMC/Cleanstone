package rocks.cleanstone.game.world;

import rocks.cleanstone.game.entity.EntityRegistry;
import rocks.cleanstone.game.world.config.WorldConfig;
import rocks.cleanstone.game.world.generation.WorldGenerator;
import rocks.cleanstone.game.world.region.RegionManager;
import rocks.cleanstone.storage.world.WorldDataSource;

public interface WorldFactory {
    World get(WorldConfig worldConfig, WorldGenerator worldGenerator, WorldDataSource worldDataSource, RegionManager regionManager, EntityRegistry entityRegistry);
}
