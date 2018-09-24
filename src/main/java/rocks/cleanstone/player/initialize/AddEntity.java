package rocks.cleanstone.player.initialize;

import java.io.IOException;
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

        try {
            EntityData entityData = playerManager.getPlayerDataSource()
                    .getPlayerData(player, StandardPlayerDataType.ENTITY_DATA);

            World world = getWorld(entityData);
            HeadRotatablePosition position = getPosition(entityData, world);

            Human human = new SimpleHuman(world, position);
            player.setEntity(human);
            world.getEntityRegistry().addEntity(human);
            player.setGameMode(getGameMode(entityData));
            player.setFlying(isFlying(entityData));
        } catch (IOException e1) {
            logger.error("Player data of " + player.getName() + " is corrupted", e1);
        }
    }

    private World getWorld(EntityData entityData) {
        return worldManager.getLoadedWorlds().stream()
                .filter(world -> worldIdMatches(entityData, world))
                .findAny()
                .orElseGet(openWorldGame::getFirstSpawnWorld);
    }

    private HeadRotatablePosition getPosition(EntityData entityData, World world) {
        return entityData == null || !worldIdMatches(entityData, world)
                ? new HeadRotatablePosition(world.getFirstSpawnPosition())
                : new HeadRotatablePosition(entityData.getLogoutPosition());
    }

    private boolean worldIdMatches(EntityData entityData, World world) {
        return world.getWorldConfig().getName().equals(entityData.getLogoutWorldID());
    }

    private GameMode getGameMode(EntityData entityData) {
        return entityData == null
                ? VanillaGameMode.CREATIVE
                : entityData.getGameMode();
    }

    private boolean isFlying(EntityData entityData) {
        return entityData != null && entityData.isFlying();
    }
}
