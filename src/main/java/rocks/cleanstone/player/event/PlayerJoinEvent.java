package rocks.cleanstone.player.event;

import lombok.Value;
import rocks.cleanstone.player.Player;

@Value
public class PlayerJoinEvent {
    Player player;

    public PlayerJoinEvent(Player player) {
        this.player = player;
    }
}
