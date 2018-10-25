package rocks.cleanstone.player.event;

import lombok.Value;
import rocks.cleanstone.player.Player;

@Value
public class PlayerQuitEvent {
    Player player;

    public PlayerQuitEvent(Player player) {
        this.player = player;
    }
}
