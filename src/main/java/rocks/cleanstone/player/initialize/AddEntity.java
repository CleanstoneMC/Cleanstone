package rocks.cleanstone.player.initialize;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

import java.io.IOException;

import rocks.cleanstone.game.OpenWorldGame;
import rocks.cleanstone.game.entity.Location;
import rocks.cleanstone.game.entity.Rotation;
import rocks.cleanstone.game.entity.vanilla.Human;
import rocks.cleanstone.game.entity.vanilla.SimpleHuman;
import rocks.cleanstone.game.gamemode.GameMode;
import rocks.cleanstone.game.gamemode.vanilla.VanillaGameMode;
import rocks.cleanstone.game.world.WorldManager;
import rocks.cleanstone.game.world.region.EntityManager;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.player.data.standard.EntityData;
import rocks.cleanstone.player.data.standard.EntityDataCodec;
import rocks.cleanstone.player.data.standard.StandardPlayerDataType;
import rocks.cleanstone.player.event.AsyncPlayerInitializationEvent;

public class AddEntity {

    private final OpenWorldGame openWorldGame;
    private final WorldManager worldManager;
    private final EntityManager entityManager;
    private final PlayerManager playerManager;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public AddEntity(OpenWorldGame openWorldGame, WorldManager worldManager, EntityManager entityManager,
                     PlayerManager playerManager) {
        this.openWorldGame = openWorldGame;
        this.worldManager = worldManager;
        this.entityManager = entityManager;
        this.playerManager = playerManager;
    }

    @Order(value = 5)
    @EventListener
    public void onJoin(AsyncPlayerInitializationEvent e) {
        Player player = e.getPlayer();

        EntityData entityData;
        try {
            entityData = playerManager.getPlayerDataSource().getPlayerData(player,
                    StandardPlayerDataType.ENTITY_DATA, new EntityDataCodec(openWorldGame, worldManager));
        } catch (IOException e1) {
            logger.error("Failed to load player data for " + player.getId().getName(), e1);
            return;
        }
        Location spawnLocation;
        GameMode gameMode;
        if (entityData == null) {
            spawnLocation = openWorldGame.getFirstSpawnWorld().getFirstSpawnLocation();
            gameMode = VanillaGameMode.CREATIVE;
        } else {
            spawnLocation = entityData.getLogoutLocation();
            gameMode = entityData.getGameMode();
        }
        Rotation spawnHeadRotation = new Rotation(spawnLocation.getRotation());

        Human human = new SimpleHuman(spawnLocation, spawnHeadRotation);
        human = entityManager.addEntityWithoutID(human);
        player.setEntity(human);
        player.setGameMode(gameMode);
    }
}
