package rocks.cleanstone.game.chat.event;

import lombok.Value;
import rocks.cleanstone.player.Player;

@Value
public class PlayerIssuedCommandEvent {

    Player player;
    String command;

    public PlayerIssuedCommandEvent(Player player, String command) {
        this.player = player;
        this.command = command;
    }
}
