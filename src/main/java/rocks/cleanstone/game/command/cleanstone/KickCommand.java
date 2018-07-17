package rocks.cleanstone.game.command.cleanstone;

import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.MessageRecipient;
import rocks.cleanstone.game.command.SimpleCommand;
import rocks.cleanstone.player.Player;

public class KickCommand extends SimpleCommand {

    public KickCommand() {
        super("kick", Player.class, String.class);
    }

    @Override
    public void execute(CommandMessage message) {
        MessageRecipient sender = message.getCommandSender();
        if (sender instanceof Player) {
            if (!((Player) sender).isOp()) {
                sender.sendRawMessage("No permission");
                return;
            }
        }

        Player target = message.requireParameter(Player.class);
        Text reason = message.optionalTextMessage().orElse(
                Text.ofLocalized("game.command.cleanstone.default-kick-reason", target.getLocale()));
        target.kick(reason);
        sender.sendMessage("game.command.cleanstone.kicked-player", target.getName(), reason);
    }
}
