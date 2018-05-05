package rocks.cleanstone.core.player.event;

import rocks.cleanstone.core.player.Player;

public class PlayerTerminationEvent {

    private final Player player;

    public PlayerTerminationEvent(Player player) {
        this.player = player;
    }
}
