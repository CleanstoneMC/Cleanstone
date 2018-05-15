package rocks.cleanstone.game.chat.event;

import rocks.cleanstone.player.Player;

public class PlayerChatMessageEvent {

    private final Player player;
    private final String message;

    public PlayerChatMessageEvent(Player player, String message) {
        this.player = player;
        this.message = message;
    }

    public Player getPlayer() {
        return player;
    }

    public String getMessage() {
        return message;
    }
}
