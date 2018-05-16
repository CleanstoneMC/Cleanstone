package rocks.cleanstone.game.command.commands;

import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.SimpleCommand;

public class StopCommand extends SimpleCommand {

    public StopCommand() {
        super("stop");
    }

    @Override
    public void execute(CommandMessage commandMessage) {
        System.exit(0);
    }
}
