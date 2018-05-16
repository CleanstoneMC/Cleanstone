package rocks.cleanstone.game.command;

import java.util.Arrays;
import java.util.List;

public class SimpleCommandMessage implements CommandMessage {

    private final CommandSender commandSender;
    private final String fullCommand;
    private final String commandName;
    private final List<String> parameters;

    public SimpleCommandMessage(CommandSender commandSender, String fullCommand) {
        this.commandSender = commandSender;
        this.fullCommand = fullCommand;

        String[] commandAndArgs = fullCommand.split(" ");

        String[] args = new String[commandAndArgs.length - 1];
        System.arraycopy(commandAndArgs, 1, args, 0, commandAndArgs.length - 1);

        this.parameters = Arrays.asList(args);

        if (fullCommand.contains(" ")) {
            commandName = fullCommand.substring(1, fullCommand.indexOf(' '));
        } else {
            commandName = fullCommand;
        }
    }

    @Override
    public CommandSender getCommandSender() {
        return commandSender;
    }

    @Override
    public String getFullMessage() {
        return fullCommand;
    }

    @Override
    public String getCommandName() {
        return commandName;
    }

    @Override
    public List<String> getParameters() {
        return parameters;
    }
}
