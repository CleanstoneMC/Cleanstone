package rocks.cleanstone.game.world;

import rocks.cleanstone.game.world.generation.WorldGenerator;

public interface WorldGeneratorManager {
    WorldGenerator getWorldGenerator(String worldGeneratorName);
}
