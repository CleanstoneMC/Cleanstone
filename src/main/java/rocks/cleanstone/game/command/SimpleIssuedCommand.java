package rocks.cleanstone.game.command;

import java.util.Arrays;
import java.util.List;

public class SimpleIssuedCommand implements IssuedCommand {

    private final CommandSender commandSender;
    private final String fullCommand;
    private final String command;
    private final List<String> parameter;

    public SimpleIssuedCommand(CommandSender commandSender, String fullCommand) {
        this.commandSender = commandSender;
        this.fullCommand = fullCommand;

        String[] subCommandArray = fullCommand.split(" ");

        String[] newSubCommandArray = new String[subCommandArray.length - 1];
        System.arraycopy(subCommandArray, 1, newSubCommandArray, 0, subCommandArray.length - 1);

        this.parameter = Arrays.asList(newSubCommandArray);

        this.command = fullCommand.substring(1, fullCommand.indexOf(' '));
    }

    @Override
    public CommandSender getCommandSender() {
        return commandSender;
    }

    @Override
    public String getFullCommand() {
        return fullCommand;
    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public List<String> getParameter() {
        return parameter;
    }
}
