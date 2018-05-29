package rocks.cleanstone.game.command.parameter;

import rocks.cleanstone.game.gamemode.GameMode;
import rocks.cleanstone.game.gamemode.vanilla.VanillaGameMode;

import javax.annotation.Nullable;
import java.util.Locale;

public class GameModeParameter implements CommandParameter<GameMode> {
    @Nullable
    @Override
    public GameMode get(String parameter) {
        parameter = parameter.toLowerCase(Locale.ENGLISH);
        if (parameter.startsWith("c")) {
            return VanillaGameMode.CREATIVE;
        } else if (parameter.startsWith("sp")) {
            return VanillaGameMode.SPECTATOR;
        } else if (parameter.startsWith("s")) {
            return VanillaGameMode.SURVIVAL;
        } else if (parameter.startsWith("a")) {
            return VanillaGameMode.ADVENTURE;
        }
        return null;
    }

    @Override
    public Class getParameterClass() {
        return GameMode.class;
    }
}
