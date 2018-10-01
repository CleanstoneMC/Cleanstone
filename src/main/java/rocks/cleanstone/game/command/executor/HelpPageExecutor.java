package rocks.cleanstone.game.command.executor;

import rocks.cleanstone.game.command.Command;
import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.CommandRegistry;
import rocks.cleanstone.game.command.SimpleCommandMessage;

public class HelpPageExecutor implements CommandExecutor {

    private final Command command;

    public HelpPageExecutor(Command command) {
        this.command = command;
    }

    @Override
    public void execute(CommandMessage message) {
        if (!(message instanceof SimpleCommandMessage)) {
            return;
        }

        final CommandRegistry commandRegistry = ((SimpleCommandMessage) message).getCommandRegistry();

        commandRegistry.getAllCommands().stream().filter(Command::showInHelp).forEach(command -> {
            message.getCommandSender().sendRawMessage(command.getUsage());
        });
    }
}
