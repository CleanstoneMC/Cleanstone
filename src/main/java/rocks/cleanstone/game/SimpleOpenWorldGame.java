package rocks.cleanstone.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.game.world.WorldManager;
import rocks.cleanstone.game.world.generation.FlatWorldGenerator;

public class SimpleOpenWorldGame implements OpenWorldGame {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final WorldManager worldManager;
    private final MaterialRegistry materialRegistry;
    private String firstSpawnWorld = null;

    @Autowired
    public SimpleOpenWorldGame(WorldManager worldManager, MaterialRegistry materialRegistry) {
        this.worldManager = worldManager;
        this.materialRegistry = materialRegistry;
    }

    public void init() {
        logger.info("Started OpenWorldGame");
        CleanstoneServer.getInstance().getMinecraftConfig().getAutoLoadWorlds().forEach(worldName -> {
            this.worldManager.loadWorld(worldName).addCallback(world -> {
                if (world == null) {
                    this.worldManager.createWorld(worldName, new FlatWorldGenerator());
                    //TODO: Change
                    // Generator
                }
            }, throwable -> {
                logger.error("Failed to load auto-load world " + worldName, throwable);
            });
        });
    }

    public void destroy() {
        logger.info("Stopping OpenWorldGame");

        this.worldManager.getLoadedWorlds().forEach(world -> this.worldManager.unloadWorld(world.getID()));

        logger.info("Stopped OpenWorldGame");
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
