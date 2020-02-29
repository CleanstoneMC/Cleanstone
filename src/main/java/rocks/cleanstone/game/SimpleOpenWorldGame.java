package rocks.cleanstone.game;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.config.GameConfig;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.game.world.WorldManager;
import rocks.cleanstone.game.world.config.WorldConfig;

import javax.annotation.Nonnull;

@Lazy
@Slf4j
@Component("game")
public class SimpleOpenWorldGame implements OpenWorldGame, SmartLifecycle {

    private final WorldManager worldManager;
    private final GameConfig gameConfig;
    private World firstSpawnWorld;
    private boolean running = false;

    @Autowired
    public SimpleOpenWorldGame(WorldManager worldManager, GameConfig gameConfig) {
        this.worldManager = worldManager;
        this.gameConfig = gameConfig;
    }

    @Override
    public World getFirstSpawnWorld() {
        return firstSpawnWorld;
    }

    @Override
    public void start() {
        gameConfig.getWorlds().stream()
                .filter(WorldConfig::isAutoload)
                .forEach(this::loadWorld);
        log.info("Started OpenWorldGame");
        running = true;
    }

    private void loadWorld(WorldConfig worldConfig) {
        try {
            World world = this.worldManager.loadWorld(worldConfig).completable().join();

            if (worldConfig.isFirstSpawnWorld()) {
                firstSpawnWorld = world;
            }
        } catch (Exception e) {
            log.error("Failed to load auto-load world " + worldConfig.getName(), e);
        }
    }

    @Override
    public void stop() {
        this.worldManager.getLoadedWorlds().forEach(world -> this.worldManager.unloadWorld(world.getWorldConfig()));
        log.info("Stopped OpenWorldGame");
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
