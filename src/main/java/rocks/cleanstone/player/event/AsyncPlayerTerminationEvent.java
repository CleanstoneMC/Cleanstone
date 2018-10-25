package rocks.cleanstone.player.event;

import lombok.Value;
import rocks.cleanstone.player.Player;

@Value
public class AsyncPlayerTerminationEvent {
    Player player;

    public AsyncPlayerTerminationEvent(Player player) {
        this.player = player;
    }
}
