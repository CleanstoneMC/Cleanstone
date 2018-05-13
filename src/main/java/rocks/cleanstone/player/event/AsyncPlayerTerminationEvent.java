package rocks.cleanstone.player.event;

import rocks.cleanstone.player.Player;

public class AsyncPlayerTerminationEvent {

    private final Player player;

    public AsyncPlayerTerminationEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
