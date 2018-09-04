package rocks.cleanstone.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.core.config.MinecraftConfig;
import rocks.cleanstone.core.config.WorldConfig;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.game.world.WorldManager;

@Component("game")
@Lazy()
public class SimpleOpenWorldGame implements OpenWorldGame {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final WorldManager worldManager;
    private final MinecraftConfig minecraftConfig;
    private WorldConfig firstSpawnWorld = null;
    private boolean running = false;

    @Autowired
    public SimpleOpenWorldGame(WorldManager worldManager, MinecraftConfig minecraftConfig) {
        this.worldManager = worldManager;
        this.minecraftConfig = minecraftConfig;
    }

    @Override
    public World getFirstSpawnWorld() {
        if (firstSpawnWorld == null) {
            CleanstoneServer.getInstance().getMinecraftConfig().getWorlds().forEach(worldConfig -> {
                if (this.firstSpawnWorld != null) {
                    throw new RuntimeException("There can only be one FirstSpawnWorld");
                }

                if (worldConfig.isFirstSpawnWorld()) {
                    this.firstSpawnWorld = worldConfig;
                }
            });
        }

        if (!worldManager.isWorldLoaded(this.firstSpawnWorld)) {
            worldManager.loadWorld(this.firstSpawnWorld);
        }

        World world = worldManager.getLoadedWorld(firstSpawnWorld);

        if (world == null) {
            throw new NullPointerException("First spawn world is not loaded");
        }

        return world;
    }

    @Override
    public void start() {
        running = true;
        logger.info("Started OpenWorldGame");
        minecraftConfig.getWorlds().forEach(worldConfig -> {
            if (!worldConfig.isAutoload()) {
                return;
            }

            this.worldManager.loadWorld(worldConfig).addCallback(world -> {
                if (world == null) {
                    this.worldManager.createWorld(worldConfig);
                    //TODO: Change
                    // Generator
                }
            }, throwable -> {
                logger.error("Failed to load auto-load world " + worldConfig, throwable);
            });
        });
    }

    @Override
    public void stop() {
        logger.info("Stopping OpenWorldGame");

        this.worldManager.getLoadedWorlds().forEach(world -> this.worldManager.unloadWorld(world.getWorldConfig()));

        logger.info("Stopped OpenWorldGame");

        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }
}
