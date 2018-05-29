package rocks.cleanstone.game.command.parameter;

import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

import javax.annotation.Nullable;

public class PlayerParameter implements CommandParameter<Player> {

    private final PlayerManager playerManager;

    public PlayerParameter(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Nullable
    @Override
    public Player get(String parameter) {
        return playerManager.getOnlinePlayer(parameter);
    }

    @Override
    public Class getParameterClass() {
        return Player.class;
    }
}
