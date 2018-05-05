package rocks.cleanstone.core.player.event;

import rocks.cleanstone.core.player.Player;

public class PlayerInitializationEvent {

    private final Player player;

    public PlayerInitializationEvent(Player player) {
        this.player = player;
    }
}
