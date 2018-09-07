package rocks.cleanstone.player.initialize;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.OpenWorldGame;
import rocks.cleanstone.game.entity.HeadRotatablePosition;
import rocks.cleanstone.game.entity.vanilla.Human;
import rocks.cleanstone.game.entity.vanilla.SimpleHuman;
import rocks.cleanstone.game.gamemode.GameMode;
import rocks.cleanstone.game.gamemode.vanilla.VanillaGameMode;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.game.world.WorldManager;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.player.data.standard.EntityData;
import rocks.cleanstone.player.data.standard.StandardPlayerDataType;
import rocks.cleanstone.player.event.AsyncPlayerInitializationEvent;

import java.io.IOException;

@Component
public class AddEntity {

    private final OpenWorldGame openWorldGame;
    private final WorldManager worldManager;
    private final PlayerManager playerManager;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public AddEntity(OpenWorldGame openWorldGame, WorldManager worldManager, PlayerManager playerManager) {
        this.openWorldGame = openWorldGame;
        this.worldManager = worldManager;
        this.playerManager = playerManager;
    }

    @Order(value = 5)
    @EventListener
    public void onInitialize(AsyncPlayerInitializationEvent e) {
        Player player = e.getPlayer();

        EntityData entityData = null;
        try {
            entityData = playerManager.getPlayerDataSource().getPlayerData(player,
                    StandardPlayerDataType.ENTITY_DATA);
        } catch (IOException e1) {
            logger.error("Player data of " + player.getName() + " is corrupted", e1);
        }
        HeadRotatablePosition position;
        GameMode gameMode;
        World world = null;
        boolean flying = false;
        if (entityData == null) {
            position = new HeadRotatablePosition(openWorldGame.getFirstSpawnWorld().getFirstSpawnPosition());
            gameMode = VanillaGameMode.CREATIVE;
        } else {
            position = new HeadRotatablePosition(entityData.getLogoutPosition());
            gameMode = entityData.getGameMode();

            for (World loadedWorld : worldManager.getLoadedWorlds()) {
                if (loadedWorld.getWorldConfig().getName().equals(entityData.getLogoutWorldID())) {
                    world = loadedWorld;
                    break;
                }
            }

            flying = entityData.isFlying();
        }
        if (world == null) {
            world = openWorldGame.getFirstSpawnWorld();
            position = new HeadRotatablePosition(world.getFirstSpawnPosition());
        }

        Human human = new SimpleHuman(world, position);
        player.setEntity(human);
        world.getEntityRegistry().addEntity(human);
        player.setGameMode(gameMode);
        player.setFlying(flying);
    }
}
