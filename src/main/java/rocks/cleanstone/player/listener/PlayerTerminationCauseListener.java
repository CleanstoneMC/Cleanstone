package rocks.cleanstone.player.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.entity.SimpleEntityRegistry;
import rocks.cleanstone.net.event.ConnectionClosedEvent;
import rocks.cleanstone.player.OnlinePlayer;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

import java.util.Optional;

@Component
public class PlayerTerminationCauseListener {

    private final PlayerManager playerManager;
    private final SimpleEntityRegistry entityRegistry;

    @Autowired
    public PlayerTerminationCauseListener(PlayerManager playerManager, SimpleEntityRegistry entityRegistry) {
        this.playerManager = playerManager;
        this.entityRegistry = entityRegistry;
    }

    @Async(value = "playerExec")
    @EventListener
    public void onPlayerConnectionClosed(ConnectionClosedEvent event) {
        final Optional<Player> optionalPlayer = playerManager.getOnlinePlayers().stream()
                .filter(player -> player instanceof OnlinePlayer)
                .filter(player -> ((OnlinePlayer) player).getConnection().equals(event.getConnection()))
                .findAny();

        optionalPlayer.ifPresent(playerManager::terminatePlayer);
    }
}
