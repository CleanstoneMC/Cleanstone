package rocks.cleanstone.game.command.cleanstone;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.SimpleCommand;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

public class AlertCommand extends SimpleCommand {

    private final PlayerManager playerManager;

    @Autowired
    public AlertCommand(PlayerManager playerManager) {
        super("alert", Arrays.asList("say", "broadcast"), String.class);
        this.playerManager = playerManager;
    }

    @Override
    public void execute(CommandMessage message) {
        if (message.getCommandSender() instanceof Player) {
            if (!((Player) message.getCommandSender()).isOp()) {
                message.getCommandSender().sendRawMessage("No permission");
                return;
            }
        }
        String inputMessage = message.requireStringMessage();

        playerManager.getOnlinePlayers().forEach(player -> {
            player.sendMessage("game.command.cleanstone.alert-format", inputMessage);
        });
    }
}
