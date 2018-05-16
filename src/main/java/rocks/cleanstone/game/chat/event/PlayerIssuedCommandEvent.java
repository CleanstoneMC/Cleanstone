package rocks.cleanstone.game.chat.event;

import rocks.cleanstone.player.Player;

public class PlayerIssuedCommandEvent {

    private final Player player;
    private final String command;

    public PlayerIssuedCommandEvent(Player player, String command) {
        this.player = player;
        this.command = command;
    }

    public Player getPlayer() {
        return player;
    }

    public String getCommand() {
        return command;
    }
}
