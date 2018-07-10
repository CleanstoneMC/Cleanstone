package rocks.cleanstone.game.command.cleanstone;

import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.SimpleCommand;

public class RestartCommand extends SimpleCommand {
    public RestartCommand() {
        super("restart", String.class);
    }

    @Override
    public void execute(CommandMessage message) {
//        if (message.getCommandSender() instanceof Player) {
//            if (!((Player) message.getCommandSender()).isOp()) {
//                message.getCommandSender().sendMessage("No permission");
//                return;
//            }
//        }

        String reason = message.requireStringMessage(true);
        if (reason.equals("")) {
            reason = CleanstoneServer.getMessage("game.command.cleanstone.default-stop-reason");
        }

        Text reasonText = Text.of(reason);
        CleanstoneServer.getInstance().restart(reasonText);
    }
}
