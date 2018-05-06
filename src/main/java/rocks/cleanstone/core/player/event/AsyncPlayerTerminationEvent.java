package rocks.cleanstone.core.player.event;

import rocks.cleanstone.core.player.Player;

public class AsyncPlayerTerminationEvent {

    private final Player player;

    public AsyncPlayerTerminationEvent(Player player) {
        this.player = player;
    }
}
