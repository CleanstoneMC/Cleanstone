package rocks.cleanstone.game.command.cleanstone;

import java.util.Arrays;

import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.SimpleCommand;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

public class AlertCommand extends SimpleCommand {

    private final PlayerManager playerManager;

    public AlertCommand(PlayerManager playerManager) {
        super("alert", Arrays.asList("say", "broadcast"));
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

        String input = message.requireStringMessage(false);
        String alertMessage = CleanstoneServer.getMessage("game.command.cleanstone.alert-format", input);
        playerManager.getOnlinePlayers().forEach(player -> player.sendMessage(Text.of(alertMessage)));
    }

    @Override
    public Class[] getExpectedParameterTypes() {
        return new Class[]{String.class};
    }
}
