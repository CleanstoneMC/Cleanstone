package rocks.cleanstone.game.command.cleanstone;

import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.SimpleCommand;
import rocks.cleanstone.game.gamemode.GameMode;
import rocks.cleanstone.player.Player;

import java.util.Collections;

public class GameModeCommand extends SimpleCommand {

    public GameModeCommand() {
        super("gamemode", Collections.singletonList("gm"));
    }

    @Override
    public void execute(CommandMessage message) {
        GameMode gameMode = message.requireParameter(GameMode.class);
        Player target = message.requireTargetPlayer();

        target.setGameMode(gameMode);
        target.sendMessage("Gamemode set to " + gameMode.toString());
    }

    @Override
    public Class[] getExpectedParameterTypes() {
        return new Class[]{GameMode.class, Player.class};
    }
}
