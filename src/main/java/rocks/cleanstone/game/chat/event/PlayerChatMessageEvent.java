package rocks.cleanstone.game.chat.event;

import lombok.Value;
import rocks.cleanstone.player.Player;

@Value
public class PlayerChatMessageEvent {

    Player player;
    String message;

    public PlayerChatMessageEvent(Player player, String message) {
        this.player = player;
        this.message = message;
    }
}
