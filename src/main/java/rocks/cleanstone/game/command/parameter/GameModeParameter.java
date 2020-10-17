package rocks.cleanstone.game.command.parameter;

import org.springframework.stereotype.Component;
import rocks.cleanstone.game.command.completion.CompletableParameter;
import rocks.cleanstone.game.command.completion.CompletionContext;
import rocks.cleanstone.game.gamemode.GameMode;
import rocks.cleanstone.game.gamemode.vanilla.VanillaGameMode;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class GameModeParameter implements CompletableParameter<GameMode> {

    @Nullable
    @Override
    public GameMode get(CompletionContext<GameMode> context) {
        for (VanillaGameMode value : VanillaGameMode.values()) {
            if (value.getName().equalsIgnoreCase(context.getInput())) {
                return value;
            }
        }

        return null;
    }

    @Override
    public Class<GameMode> getParameterClass() {
        return GameMode.class;
    }

    @Override
    public List<String> getCompletion(CompletionContext<GameMode> context) {
        return Arrays.stream(VanillaGameMode.values())
                .map(VanillaGameMode::getName)
                .filter(playerName -> playerName.toLowerCase(Locale.ENGLISH)
                        .startsWith(context.getInput().toLowerCase(Locale.ENGLISH)))
                .collect(Collectors.toList());
    }
}
