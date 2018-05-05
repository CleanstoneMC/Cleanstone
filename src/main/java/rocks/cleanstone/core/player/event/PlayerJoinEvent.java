package rocks.cleanstone.core.player.event;

import rocks.cleanstone.core.player.Player;

public class PlayerJoinEvent {

    private final Player player;

    public PlayerJoinEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
