package rocks.cleanstone.game.command.cleanstone;

import org.springframework.stereotype.Component;
import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.MessageRecipient;
import rocks.cleanstone.game.command.SimpleCommand;
import rocks.cleanstone.player.Player;

@Component
public class KickCommand extends SimpleCommand {

    public KickCommand() {
        super("kick", Player.class, String.class);
    }

    @Override
    public void execute(CommandMessage message) {
        final MessageRecipient sender = message.getCommandSender();
        if (sender instanceof Player) {
            if (!((Player) sender).isOp()) {
                sender.sendRawMessage("No permission");
                return;
            }
        }

        final Player target = message.requireParameter(Player.class);
        final Text reason = message.optionalTextMessage().orElse(
                Text.ofLocalized("game.command.cleanstone.default-kick-reason", target.getLocale()));
        target.kick(reason);
        sender.sendMessage("game.command.cleanstone.kicked-player", target.getName(), reason);
    }
}
