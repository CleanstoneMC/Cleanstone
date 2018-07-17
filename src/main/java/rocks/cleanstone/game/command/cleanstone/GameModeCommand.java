package rocks.cleanstone.game.command.cleanstone;

import java.util.Collections;

import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.SimpleCommand;
import rocks.cleanstone.game.gamemode.GameMode;
import rocks.cleanstone.game.gamemode.vanilla.VanillaGameMode;
import rocks.cleanstone.net.packet.enums.GameStateChangeReason;
import rocks.cleanstone.net.packet.outbound.ChangeGameStatePacket;
import rocks.cleanstone.player.Player;

public class GameModeCommand extends SimpleCommand {

    public GameModeCommand() {
        super("gamemode", Collections.singletonList("gm"), VanillaGameMode.class, Player.class);
    }

    @Override
    public void execute(CommandMessage message) {
        GameMode gameMode = message.requireParameter(VanillaGameMode.class);
        Player target = message.requireTargetPlayer();
        boolean gameModeChanged = target.getGameMode() != gameMode;

        if (gameModeChanged) {
            target.setGameMode(gameMode);
            target.sendPacket(new ChangeGameStatePacket(GameStateChangeReason.CHANGE_GAMEMODE,
                    gameMode.getTypeId()));
            target.sendMessage("game.command.cleanstone.own-gamemode-changed", gameMode.getName());
        }
        if (target != message.getCommandSender() || !gameModeChanged) {
            message.getCommandSender().sendMessage("game.command.cleanstone.changed-gamemode",
                    target.getName(), gameMode.getName());
        }
    }
}
