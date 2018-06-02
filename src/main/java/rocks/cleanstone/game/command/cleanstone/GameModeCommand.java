package rocks.cleanstone.game.command.cleanstone;

import java.util.Collections;

import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.SimpleCommand;
import rocks.cleanstone.game.gamemode.GameMode;
import rocks.cleanstone.net.packet.enums.GameStateChangeReason;
import rocks.cleanstone.net.packet.outbound.ChangeGameStatePacket;
import rocks.cleanstone.player.Player;

public class GameModeCommand extends SimpleCommand {

    public GameModeCommand() {
        super("gamemode", Collections.singletonList("gm"));
    }

    @Override
    public void execute(CommandMessage message) {
        GameMode gameMode = message.requireParameter(GameMode.class);
        Player target = message.requireTargetPlayer();
        boolean gameModeChanged = target.getGameMode() != gameMode;

        if (gameModeChanged) {
            target.setGameMode(gameMode);
            target.sendPacket(new ChangeGameStatePacket(GameStateChangeReason.CHANGE_GAMEMODE,
                    gameMode.getTypeId()));
            target.sendMessage(CleanstoneServer.getMessage("game.command.cleanstone.own-gamemode-changed",
                    gameMode.getName()));
        }
        if (target != message.getCommandSender() || !gameModeChanged) {
            message.getCommandSender().sendMessage(CleanstoneServer.getMessage(
                    "game.command.cleanstone.changed-gamemode", target.getId().getName(), gameMode.getName()));
        }
    }

    @Override
    public Class[] getExpectedParameterTypes() {
        return new Class[]{GameMode.class, Player.class};
    }
}
