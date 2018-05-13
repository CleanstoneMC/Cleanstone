package rocks.cleanstone.player.event;

import rocks.cleanstone.player.Player;

public class PlayerJoinEvent {

    private final Player player;

    public PlayerJoinEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
