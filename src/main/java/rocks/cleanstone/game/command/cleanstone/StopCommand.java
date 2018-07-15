package rocks.cleanstone.game.command.cleanstone;

import org.springframework.beans.factory.annotation.Autowired;

import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.SimpleCommand;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

public class StopCommand extends SimpleCommand {

    private final PlayerManager playerManager;

    @Autowired
    public StopCommand(PlayerManager playerManager) {
        super("stop", String.class);
        this.playerManager = playerManager;
    }

    @Override
    public void execute(CommandMessage message) {
        if (message.getCommandSender() instanceof Player) {
            if (!((Player) message.getCommandSender()).isOp()) {
                message.getCommandSender().sendMessage("No permission");
                return;
            }
        }

        String reason = message.requireStringMessage(true);
        if (reason.equals("")) {
            reason = CleanstoneServer.getMessage("game.command.cleanstone.default-stop-reason");
        }

        Text reasonText = Text.of(reason);
        playerManager.getOnlinePlayers().forEach(player->player.kick(reasonText));
        CleanstoneServer.stop();
        System.exit(0);
    }
}
