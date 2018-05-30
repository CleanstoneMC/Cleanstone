package rocks.cleanstone.game.command.cleanstone;

import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.SimpleCommand;

public class SetCommand extends SimpleCommand {

    public SetCommand() {
        super("set");
        addSubCommand(new Name(), "name");
    }

    private class Name extends SimpleCommand {
        public Name() {
            super("name");
        }

        @Override
        public void execute(CommandMessage commandMessage) {

        }
    }
}
