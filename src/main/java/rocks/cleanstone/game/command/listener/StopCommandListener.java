package rocks.cleanstone.game.command.listener;

import rocks.cleanstone.game.command.IssuedCommand;
import rocks.cleanstone.game.command.SimpleCommand;

public class StopCommandListener extends SimpleCommand {

    @Override
    public String getCommandString() {
        return "stop";
    }

    @Override
    public void execute(IssuedCommand issuedCommand) {
        System.exit(0);
    }
}
