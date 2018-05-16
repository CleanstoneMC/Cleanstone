package rocks.cleanstone.game.command.commands;

import rocks.cleanstone.game.command.IssuedCommand;
import rocks.cleanstone.game.command.SimpleCommand;

public class StopCommand extends SimpleCommand {

    @Override
    public String getCommandString() {
        return "stop";
    }

    @Override
    public void execute(IssuedCommand issuedCommand) {
        System.exit(0);
    }
}
