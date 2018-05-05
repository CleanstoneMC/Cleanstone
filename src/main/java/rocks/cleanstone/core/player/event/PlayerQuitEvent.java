package rocks.cleanstone.core.player.event;

import rocks.cleanstone.core.player.Player;

public class PlayerQuitEvent {

    private final Player player;

    public PlayerQuitEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
