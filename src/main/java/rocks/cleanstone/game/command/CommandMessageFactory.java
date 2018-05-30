package rocks.cleanstone.game.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class CommandMessageFactory {

    private CommandMessageFactory() {
    }

    public static CommandMessage construct(CommandSender commandSender, String message,
                                           CommandRegistry commandRegistry) {
        String messageWithoutSlash = getMessageWithoutSlash(message);

        List<String> parameters = new ArrayList<>(Arrays.asList(messageWithoutSlash.split(" ")));
        String commandName = parameters.remove(0).toLowerCase(Locale.ENGLISH);

        return new SimpleCommandMessage(commandSender, message, commandName, parameters, commandRegistry);
    }

    private static String getMessageWithoutSlash(String message) {
        if (message.isEmpty()) return "";
        if (message.charAt(0) == '/') {
            message = message.replaceFirst("/", "");
        }
        return message;
    }
}
