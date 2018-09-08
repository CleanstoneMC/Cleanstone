package rocks.cleanstone.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.config.MinecraftConfig;
import rocks.cleanstone.core.config.WorldConfig;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.game.world.WorldManager;

import javax.annotation.Nonnull;
import java.util.concurrent.ExecutionException;

@Component("game")
@Lazy()
public class SimpleOpenWorldGame implements OpenWorldGame, SmartLifecycle {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final WorldManager worldManager;
    private final MinecraftConfig minecraftConfig;
    private World firstSpawnWorld;
    private boolean running = false;

    @Autowired
    public SimpleOpenWorldGame(WorldManager worldManager, MinecraftConfig minecraftConfig) {
        this.worldManager = worldManager;
        this.minecraftConfig = minecraftConfig;
    }

    @Override
    public World getFirstSpawnWorld() {
        return firstSpawnWorld;
    }

    @Override
    public void start() {
        minecraftConfig.getWorlds().stream().filter(WorldConfig::isAutoload).forEach(worldConfig -> {
            try {
                World world = this.worldManager.loadWorld(worldConfig).get();
                if (worldConfig.isFirstSpawnWorld()) {
                    firstSpawnWorld = world;
                }
            } catch (InterruptedException | ExecutionException e) {
                logger.error("Failed to load auto-load world " + worldConfig.getName(), e);
            }
        });
        logger.info("Started OpenWorldGame");
        running = true;
    }

    @Override
    public void stop() {
        this.worldManager.getLoadedWorlds().forEach(world -> this.worldManager.unloadWorld(world.getWorldConfig()));
        logger.info("Stopped OpenWorldGame");
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(@Nonnull Runnable callback) {
        stop();
        callback.run();
    }

    @Override
    public int getPhase() {
        return 5;
    }
}
