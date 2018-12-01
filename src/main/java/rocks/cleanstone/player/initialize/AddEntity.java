package rocks.cleanstone.player.initialize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import rocks.cleanstone.game.OpenWorldGame;
import rocks.cleanstone.game.entity.EntityTracker;
import rocks.cleanstone.game.entity.HeadRotatablePosition;
import rocks.cleanstone.game.entity.cleanstone.Human;
import rocks.cleanstone.game.entity.cleanstone.SimpleHuman;
import rocks.cleanstone.game.gamemode.GameMode;
import rocks.cleanstone.game.gamemode.vanilla.VanillaGameMode;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.game.world.WorldManager;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.player.data.standard.EntityData;
import rocks.cleanstone.player.data.standard.StandardPlayerDataType;
import rocks.cleanstone.player.event.AsyncPlayerInitializationEvent;

@Slf4j
@Component
public class AddEntity {

    private final OpenWorldGame openWorldGame;
    private final WorldManager worldManager;
    private final PlayerManager playerManager;
    private final EntityTracker entityTracker;

    @Autowired
    public AddEntity(OpenWorldGame openWorldGame, WorldManager worldManager, PlayerManager playerManager,
                     EntityTracker entityTracker) {
        this.openWorldGame = openWorldGame;
        this.worldManager = worldManager;
        this.playerManager = playerManager;
        this.entityTracker = entityTracker;
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

            Human human = new SimpleHuman(world, position, false, 20); // TODO save and read health,glowing
            log.debug("{} now has entity id {}", player.getFormattedName(), human.getEntityID());
            player.setEntity(human);
            world.getEntityRegistry().addEntity(human);
            player.setGameMode(getGameMode(entityData));
            player.setFlying(isFlying(entityData));
            entityTracker.addObserver(human);
        } catch (IOException e1) {
            log.error("Player data of " + player.getFormattedName() + " is corrupted", e1);
        }
    }

    private World getWorld(EntityData entityData) {
        World world = worldManager.getLoadedWorld(entityData.getLogoutWorldID());
        return world != null ? world : openWorldGame.getFirstSpawnWorld();
    }

    private HeadRotatablePosition getPosition(EntityData entityData, World world) {
        return world.getID().equals(entityData.getLogoutWorldID())
                ? new HeadRotatablePosition(entityData.getLogoutPosition())
                : new HeadRotatablePosition(world.getFirstSpawnPosition());
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
