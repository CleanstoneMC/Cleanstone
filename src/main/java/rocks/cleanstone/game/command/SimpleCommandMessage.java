package rocks.cleanstone.game.command;

import java.util.List;

public class SimpleCommandMessage implements CommandMessage {

    private final CommandSender commandSender;
    private final String fullCommand;
    private final String commandName;
    private final List<String> parameters;

    public SimpleCommandMessage(CommandSender commandSender, String fullCommand, String commandName, List<String> parameters) {
        this.commandSender = commandSender;
        this.fullCommand = fullCommand;
        this.commandName = commandName;
        this.parameters = parameters;
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
