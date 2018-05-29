package rocks.cleanstone.game.command.executor;

import rocks.cleanstone.game.command.Command;
import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.InvalidParameterException;
import rocks.cleanstone.game.command.NotEnoughParametersException;

import java.util.Locale;

public class SubCommandExecutor implements CommandExecutor {

    private final Command command;

    public SubCommandExecutor(Command command) {
        this.command = command;
    }

    @Override
    public void execute(CommandMessage commandMessage) {
        String parameter = commandMessage.requireParameter(String.class);
        Command subCommand = command.getSubCommands().get(parameter.toLowerCase(Locale.ENGLISH));
        if (subCommand != null) {
            try {
                subCommand.execute(commandMessage);
            } catch (InvalidParameterException | NotEnoughParametersException e) {
                new HelpPageExecutor(subCommand).execute(commandMessage);
            }
        } else {
            new HelpPageExecutor(command).execute(commandMessage);
        }
    }
}
