package rocks.cleanstone.game.chat.event;

import rocks.cleanstone.player.Player;

public class PlayerIssuedCommandEvent {

    private final Player player;
    private final String command;
    private final String[] parameter;

    public PlayerIssuedCommandEvent(Player player, String command, String[] parameter) {
        this.player = player;
        this.command = command;
        this.parameter = parameter;
    }

    public Player getPlayer() {
        return player;
    }

    public String getCommand() {
        return command;
    }

    public String[] getParameter() {
        return parameter;
    }
}
