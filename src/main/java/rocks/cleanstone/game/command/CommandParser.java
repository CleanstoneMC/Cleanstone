package rocks.cleanstone.game.command;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Stack;

public class CommandParser {
    private final CommandRegistry commandRegistry;

    public CommandParser(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

    public String getCommandMessageWithoutSlash(String message) {
        if (message.charAt(0) == '/') {
            message = message.replaceFirst("/", "");
        }

        return message;
    }

    @Nullable
    public Command getMainCommandFromMessage(String message) {
        message = getCommandMessageWithoutSlash(message);

        String[] split = message.split(" ");

        String mainCommand = split[0];

        return commandRegistry.getCommand(mainCommand);
    }

    @Nullable
    public Command getCommandFromString(String commandName) {
        Command command = getMainCommandFromMessage(commandName);
        String[] split = commandName.split(" ");

        if (command == null) {
            return null;
        }

        if (split.length == 1) {
            return command;
        }

        for (int i = 1; i < split.length; i++) {
            String part = split[i];

            if (command == null) {
                return null;
            }

            command = command.getSubCommands().get(part);
        }

        return command;
    }

    @Nullable
    public CommandMessage getCommandMessageFromMessage(CommandSender commandSender, String message) {
        final String messageWithoutSlash = getCommandMessageWithoutSlash(message);

        Stack<String> strings = new Stack<>();
        strings.addAll(Arrays.asList(message.split(" ")));

        final int neededParams = 0;
        Command command = commandRegistry.getCommand(strings.pop());
        while (!strings.empty() && strings.size() > neededParams) {
            if (command == null) {
                return null;
            }

            String currentPart = strings.pop();

            if (command.getSubCommands().containsKey(currentPart)) {
                command = command.getSubCommands().get(currentPart);
            } else {
                break;
            }
        }

        StringBuilder commandParameterBuilder = new StringBuilder();
        for (int i = 0, stringsSize = strings.size(); i < stringsSize; i++) {
            commandParameterBuilder.append(strings.get(i));

            if (i != stringsSize - 1) {
                commandParameterBuilder.append(" ");
            }
        }

        String commandParameter = commandParameterBuilder.toString();

        String commandString = messageWithoutSlash.substring(commandParameter.length() * -1);

        return new SimpleCommandMessage(commandSender, message, commandString, strings, commandRegistry);
    }
}
