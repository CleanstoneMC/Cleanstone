package rocks.cleanstone.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.Lifecycle;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.core.config.MinecraftConfig;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.game.world.WorldManager;

@Component("game")
@Lazy()
public class SimpleOpenWorldGame implements OpenWorldGame, Lifecycle {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final WorldManager worldManager;
    private final MinecraftConfig minecraftConfig;
    private String firstSpawnWorld = null;
    private boolean running = false;

    @Autowired
    public SimpleOpenWorldGame(WorldManager worldManager, MinecraftConfig minecraftConfig) {
        this.worldManager = worldManager;
        this.minecraftConfig = minecraftConfig;
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

    @Override
    public void start() {
        running = true;
        logger.info("Started OpenWorldGame");
        minecraftConfig.getAutoLoadWorlds().forEach(worldName -> {
            this.worldManager.loadWorld(worldName).addCallback(world -> {
                if (world == null) {
                    this.worldManager.createWorld(worldName, null);
                    //TODO: Change
                    // Generator
                }
            }, throwable -> {
                logger.error("Failed to load auto-load world " + worldName, throwable);
            });
        });
    }

    @Override
    public void stop() {
        logger.info("Stopping OpenWorldGame");

        this.worldManager.getLoadedWorlds().forEach(world -> this.worldManager.unloadWorld(world.getID()));

        logger.info("Stopped OpenWorldGame");

        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }
}
