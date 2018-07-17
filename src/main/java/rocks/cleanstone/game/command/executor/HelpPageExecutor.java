package rocks.cleanstone.game.command.executor;

import rocks.cleanstone.game.command.Command;
import rocks.cleanstone.game.command.CommandMessage;

public class HelpPageExecutor implements CommandExecutor {

    private final Command command;

    public HelpPageExecutor(Command command) {
        this.command = command;
    }

    @Override
    public void execute(CommandMessage message) {
        // TODO
    }
}
