package rocks.cleanstone.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.game.world.WorldManager;
import rocks.cleanstone.game.world.generation.FlatWorldGenerator;

public class SimpleOpenWorldGame implements OpenWorldGame {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final WorldManager worldManager;

    public SimpleOpenWorldGame(WorldManager worldManager) {

        this.worldManager = worldManager;
    }

    public void init() {
        logger.info("Startet Openworldgame");
    }

    @Override
    public World getFirstSpawnWorld() {

        if (!worldManager.isWorldLoaded("world")) {
            worldManager.createWorld("world", new FlatWorldGenerator()); //TODO: Load and the create
        }

        World world = worldManager.getLoadedWorld("world");

        if (world == null) {
            throw new NullPointerException();
        }

        return world;
    }
}
