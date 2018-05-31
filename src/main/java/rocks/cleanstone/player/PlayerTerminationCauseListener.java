package rocks.cleanstone.player;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import rocks.cleanstone.net.event.ConnectionClosedEvent;

import java.util.Optional;

public class PlayerTerminationCauseListener {

    private final PlayerManager playerManager;

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
