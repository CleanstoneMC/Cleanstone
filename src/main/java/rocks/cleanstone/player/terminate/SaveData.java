package rocks.cleanstone.player.terminate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

import java.io.IOException;

import rocks.cleanstone.game.OpenWorldGame;
import rocks.cleanstone.game.world.WorldManager;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.player.data.standard.EntityData;
import rocks.cleanstone.player.data.standard.StandardPlayerDataType;
import rocks.cleanstone.player.event.AsyncPlayerTerminationEvent;

public class SaveData {

    private final PlayerManager playerManager;
    private final OpenWorldGame openWorldGame;
    private final WorldManager worldManager;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public SaveData(PlayerManager playerManager, OpenWorldGame openWorldGame, WorldManager worldManager) {
        this.playerManager = playerManager;
        this.openWorldGame = openWorldGame;
        this.worldManager = worldManager;
    }

    @Order(value = 50)
    @EventListener
    public void onJoin(AsyncPlayerTerminationEvent e) {
        Player player = e.getPlayer();
        EntityData entityData = new EntityData(player.getEntity().getPosition(),
                player.getEntity().getWorld().getID(), player.getGameMode(), player.isFlying());
        try {
            playerManager.getPlayerDataSource().setPlayerData(player, StandardPlayerDataType.ENTITY_DATA,
                    entityData);
        } catch (IOException e1) {
            logger.error("Failed to save player data for " + player, e1);
        }
    }
}
