package rocks.cleanstone.game.command.parameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.command.completion.CompletableParameter;
import rocks.cleanstone.game.command.completion.CompletionContext;
import rocks.cleanstone.game.command.completion.CompletionMatch;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class PlayerParameter implements CompletableParameter<Player> {

    private final PlayerManager playerManager;

    @Autowired
    public PlayerParameter(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Nullable
    @Override
    public Player get(CompletionContext<Player> context) {
        return playerManager.getOnlinePlayer(context.getInput());
    }

    @Override
    public Class<Player> getParameterClass() {
        return Player.class;
    }

    @Override
    public List<CompletionMatch> getCompletion(CompletionContext<Player> context) {
        return playerManager.getOnlinePlayers().stream()
                .map(player -> player.getID().getName())
                .filter(playerName -> playerName.toLowerCase(Locale.ENGLISH)
                        .startsWith(context.getInput().toLowerCase(Locale.ENGLISH)))
                .map(CompletionMatch::new)
                .collect(Collectors.toList());
    }
}
