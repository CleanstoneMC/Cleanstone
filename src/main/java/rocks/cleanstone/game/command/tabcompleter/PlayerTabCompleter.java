package rocks.cleanstone.game.command.tabcompleter;

import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

import java.util.Locale;

public class PlayerTabCompleter implements TabCompleter {
    private final PlayerManager playerManager;

    public PlayerTabCompleter(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Override
    public Class getCompletingClass() {
        return Player.class;
    }

    @Override
    public String[] getCompletion() {
        Player[] players = (Player[]) playerManager.getOnlinePlayers().toArray();
        String[] playerNames = new String[players.length];

        for (int i = 0; i < players.length; i++) {
            playerNames[i] = players[i].getId().getName();
        }

        return playerNames;
    }

    @Override
    public String[] getCompletion(String message) {
        Player[] players = (Player[]) playerManager.getOnlinePlayers().stream().filter(
                player -> player.getId().getName().toLowerCase(Locale.ENGLISH)
                        .startsWith(message.toLowerCase(Locale.ENGLISH))).toArray();
        String[] playerNames = new String[players.length];

        for (int i = 0; i < players.length; i++) {
            playerNames[i] = players[i].getId().getName();
        }

        return playerNames;
    }
}
