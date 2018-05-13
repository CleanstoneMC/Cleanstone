package rocks.cleanstone.player.event;

import rocks.cleanstone.player.Player;

public class PlayerQuitEvent {

    private final Player player;

    public PlayerQuitEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
