package rocks.cleanstone.player.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.event.ConnectionClosedEvent;
import rocks.cleanstone.player.OnlinePlayer;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

import java.util.Optional;

@Component
public class PlayerTerminationCauseListener {

    private final PlayerManager playerManager;

    @Autowired
    public PlayerTerminationCauseListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Async(value = "playerExec")
    @EventListener
    public void onPlayerConnectionClosed(ConnectionClosedEvent event) {
        Optional<Player> optionalPlayer = playerManager.getOnlinePlayers().stream()
                .filter(player -> player instanceof OnlinePlayer)
                .filter(player -> ((OnlinePlayer) player).getConnection().equals(event.getConnection()))
                .findAny();

        optionalPlayer.ifPresent(playerManager::terminatePlayer);
    }
}
