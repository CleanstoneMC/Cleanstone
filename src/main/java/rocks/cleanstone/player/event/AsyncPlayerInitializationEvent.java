package rocks.cleanstone.player.event;

import lombok.Value;
import rocks.cleanstone.player.Player;

@Value
public class AsyncPlayerInitializationEvent {
    Player player;

    public AsyncPlayerInitializationEvent(Player player) {
        this.player = player;
    }
}
