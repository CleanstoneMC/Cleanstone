package rocks.cleanstone.game.command.parameter;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import rocks.cleanstone.game.command.completion.CompletableValue;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

public class PlayerParameter implements CommandParameter<Player>, CompletableValue<Player> {

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
    public Class<Player> getParameterClass() {
        return Player.class;
    }

    @Override
    public List<String> getCompletion(Class<Player> parameterClass, String message) {
        return playerManager.getOnlinePlayers().stream()
                .map(player -> player.getId().getName())
                .filter(playerName -> playerName.toLowerCase(Locale.ENGLISH)
                        .startsWith(message.toLowerCase(Locale.ENGLISH)))
                .collect(Collectors.toList());
    }
}
