package rocks.cleanstone.game.command;

import javax.annotation.Nullable;

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
    public Command getCommandMainCommandFromMessage(String message) {
        message = getCommandMessageWithoutSlash(message);

        String[] split = message.split(" ");

        String mainCommand = split[0];


        return null;
        //return commandRegistry.getCommandByName(mainCommand);
    }
}
