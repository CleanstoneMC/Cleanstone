package rocks.cleanstone.core.player;

import org.springframework.context.event.EventListener;

import rocks.cleanstone.net.event.ConnectionClosedEvent;

public class PlayerTerminationCauseListener {

    private final PlayerManager playerManager;

    public PlayerTerminationCauseListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @EventListener
    public void onPlayerConnectionClosed(ConnectionClosedEvent event) {
        for (Player player : playerManager.getOnlinePlayers()) {
            if (player instanceof OnlinePlayer) {
                OnlinePlayer onlinePlayer = (OnlinePlayer) player;
                if (onlinePlayer.getConnection().equals(event.getConnection())) {
                    playerManager.terminatePlayer(onlinePlayer);
                }
            }
        }
    }
}
