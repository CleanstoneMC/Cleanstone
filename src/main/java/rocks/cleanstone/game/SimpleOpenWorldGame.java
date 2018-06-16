package rocks.cleanstone.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.game.world.WorldManager;
import rocks.cleanstone.game.world.generation.FlatWorldGenerator;

public class SimpleOpenWorldGame implements OpenWorldGame {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final WorldManager worldManager;
    private String firstSpawnWorld = null;

    public SimpleOpenWorldGame(WorldManager worldManager) {

        this.worldManager = worldManager;
    }

    public void init() {
        logger.info("Started OpenWorldGame");
        CleanstoneServer.getInstance().getMinecraftConfig().getAutoLoadWorlds().forEach(worldName -> {
            this.worldManager.loadWorld(worldName).addCallback(world -> {
                if (world == null) {
                    this.worldManager.createWorld(worldName, new FlatWorldGenerator()); //TODO: Change Generator
                }
            }, throwable -> {
                //TODO: What to do here?
            });
        });

    }

    @Override
    public World getFirstSpawnWorld() {
        if (firstSpawnWorld == null) {
            this.firstSpawnWorld = CleanstoneServer.getInstance().getMinecraftConfig().getFirstSpawnWorld();
        }

        if (!worldManager.isWorldLoaded(this.firstSpawnWorld)) {
            worldManager.loadWorld(this.firstSpawnWorld);
        }

        World world = worldManager.getLoadedWorld("world");

        if (world == null) {
            throw new NullPointerException("First spawn world is not loaded");
        }

        return world;
    }
}
