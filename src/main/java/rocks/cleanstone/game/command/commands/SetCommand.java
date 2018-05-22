package rocks.cleanstone.game.command.commands;

import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.SimpleCommand;
import rocks.cleanstone.player.Player;

public class SetCommand extends SimpleCommand {
    public SetCommand() {
        super("set");
    }

    @Override
    public void execute(CommandMessage commandMessage) {
        commandMessage.getCommandSender().sendMessage("Please specify the Subcommand");
    }

    private class Name extends SimpleCommand {
        public Name() {
            super("name");
        }

        @Override
        public int getMinimumParameters() {
            return 1;
        }

        @Override
        public Class[] getParameterTypes() {
            return new Class[]{Player.class};
        }

        @Override
        public void execute(CommandMessage commandMessage) {

        }
    }
}
