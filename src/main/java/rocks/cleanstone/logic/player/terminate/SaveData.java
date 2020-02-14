package rocks.cleanstone.logic.player.terminate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.OpenWorldGame;
import rocks.cleanstone.game.world.WorldManager;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.player.data.standard.EntityData;
import rocks.cleanstone.player.data.standard.StandardPlayerDataType;
import rocks.cleanstone.player.event.AsyncPlayerTerminationEvent;

import java.io.IOException;

@Slf4j
@Component
public class SaveData {

    private final PlayerManager playerManager;
    private final OpenWorldGame openWorldGame;
    private final WorldManager worldManager;

    @Autowired
    public SaveData(PlayerManager playerManager, OpenWorldGame openWorldGame, WorldManager worldManager) {
        this.playerManager = playerManager;
        this.openWorldGame = openWorldGame;
        this.worldManager = worldManager;
    }

    @Order(value = 50)
    @EventListener
    public void onTerminate(AsyncPlayerTerminationEvent e) {
        Player player = e.getPlayer();
        if (player.getEntity() == null) {
            return;
        }
        EntityData entityData = new EntityData(player.getEntity().getPosition(),
                player.getEntity().getWorld().getID(), player.getGameMode(), player.isFlying());
        try {
            playerManager.getPlayerDataSource().setPlayerData(player, StandardPlayerDataType.ENTITY_DATA,
                    entityData);
        } catch (IOException e1) {
            log.error("Failed to save player data for " + player, e1);
        }
    }
}
