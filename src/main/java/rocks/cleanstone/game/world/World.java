package rocks.cleanstone.game.world;

import rocks.cleanstone.game.entity.Location;
import rocks.cleanstone.game.world.region.Region;
import rocks.cleanstone.game.world.region.RegionManager;
import rocks.cleanstone.net.packet.enums.Difficulty;
import rocks.cleanstone.net.packet.enums.Dimension;
import rocks.cleanstone.net.packet.enums.LevelType;

public interface World {
    String getID();

    Dimension getDimension();

    Difficulty getDifficulty();

    LevelType getLevelType();

    Location getFirstSpawnLocation();
}
