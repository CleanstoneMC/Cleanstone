package rocks.cleanstone.game.command.commands;

import rocks.cleanstone.game.chat.message.Chat;
import rocks.cleanstone.game.command.IssuedCommand;
import rocks.cleanstone.game.command.SimpleCommand;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

public class OpCommand extends SimpleCommand {
    private final PlayerManager playerManager;

    public OpCommand(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Override
    public String getCommandString() {
        return "op";
    }

    @Override
    public void execute(IssuedCommand issuedCommand) {
        String playerName = issuedCommand.getParameter().get(0);
        Player player = playerManager.getOnlinePlayer(playerName);

        if (player == null) {
            logger.info("Could not find Player: {}", playerName);
            return;
        }

        player.setOp(!player.isOp());

        if (player.isOp()) {
            player.sendMessage(new Chat("You are now an Operator"));
        } else {
            player.sendMessage(new Chat("You are no longer an Operator"));
        }
    }

    @Override
    public int neededParameter() {
        return 1;
    }
}
