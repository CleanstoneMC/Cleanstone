package rocks.cleanstone.player.event;

import rocks.cleanstone.player.Player;

public class AsyncPlayerInitializationEvent {

    private final Player player;

    public AsyncPlayerInitializationEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
