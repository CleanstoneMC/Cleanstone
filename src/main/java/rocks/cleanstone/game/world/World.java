package rocks.cleanstone.game.world;

import rocks.cleanstone.game.world.region.RegionManager;
import rocks.cleanstone.net.minecraft.packet.enums.Difficulty;
import rocks.cleanstone.net.minecraft.packet.enums.Dimension;
import rocks.cleanstone.net.minecraft.packet.enums.LevelType;

public interface World extends RegionManager {
    String getID();

    Dimension getDimension();

    Difficulty getDifficulty();

    LevelType getLevelType();
}
