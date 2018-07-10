package rocks.cleanstone.game.command.cleanstone;

import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.SimpleCommand;
import rocks.cleanstone.player.Player;

public class KickCommand extends SimpleCommand {

    public KickCommand() {
        super("kick", Player.class, String.class);
    }

    @Override
    public void execute(CommandMessage message) {
        if (message.getCommandSender() instanceof Player) {
            if (!((Player) message.getCommandSender()).isOp()) {
                message.getCommandSender().sendMessage("No permission");
                return;
            }
        }

        Player target = message.requireParameter(Player.class);
        String reason = message.optionalParameter(String.class)
                .orElseGet(() -> CleanstoneServer.getMessage("game.command.cleanstone.default-kick-reason"));
        target.kick(Text.of(reason));
        message.getCommandSender().sendMessage(Text.of(CleanstoneServer.getMessage(
                "game.command.cleanstone.kicked-player", target.getId().getName(), reason)));
    }

    @Override
    public Class[] getExpectedParameterTypes() {
        return new Class[]{Player.class, String.class};
    }
}
