package rocks.cleanstone.core.player.event;

import rocks.cleanstone.core.player.Player;

public class AsyncPlayerInitializationEvent {

    private final Player player;

    public AsyncPlayerInitializationEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
