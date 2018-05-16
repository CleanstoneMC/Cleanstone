package rocks.cleanstone.game.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class CommandRegistry {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private Map<String, String> aliasMap = new HashMap<>();
    private Map<String, Command> commandMap = new HashMap<>();

    @Nullable
    public Command getCommand(String commandName) {
        if (aliasMap.containsKey(commandName)) {
            return commandMap.get(aliasMap.get(commandName));
        }

        if (commandMap.containsKey(commandName)) {
            return commandMap.get(commandName);
        }

        return null;
    }

    public boolean hasCommand(String commandName) {
        return aliasMap.containsKey(commandName) || commandMap.containsKey(commandName);
    }

    public boolean isAlias(String command) {
        return aliasMap.containsKey(command);
    }

    public void registerCommand(Command command) {
        registerCommand(command, false);
    }

    public void registerCommand(Command command, boolean force) {

        if (command instanceof SubCommand) {
            logger.error("Command \"{}\" is a SubCommand. Do not do that!", command.getName());
            return;
        }

        if (!force && commandMap.containsKey(command.getName())) {
            logger.error("Command \"{}\" is already registered", command.getName());
            return;
        }

        commandMap.put(command.getName(), command);

        if (command.getAliases() != null) {
            command.getAliases().forEach(s -> aliasMap.put(s, command.getName()));
        }

    }

    public void executeCommand(String commandString, CommandMessage commandMessage) {

        Command command = getCommand(commandString);

        if (command == null) {
            logger.error("Command \"{}\" does not exist", commandString);
            return;
        }

        Command correctSubCommand = getCorrectSubCommand(commandString, command);

        correctSubCommand.execute(commandMessage);
    }

    private Command getCorrectSubCommand(String commandString, Command command) {
        if (command.getSubCommands() != null) {
            String[] subCommandArray = commandString.split(" ");
            if (subCommandArray.length < 2) {
                return command;
            }

            String subCommandName = subCommandArray[1];

            boolean contains = command.getSubCommands().containsKey(subCommandName);

            if (!contains) {
                return command;
            }

            SubCommand subCommand = command.getSubCommands().get(subCommandName);

            String[] subCommandStringArray = new String[subCommandArray.length - 1];
            System.arraycopy(subCommandArray, 1, subCommandStringArray, 0, subCommandArray.length - 1);
            String subCommandString = String.join(" ", subCommandStringArray);

            return getCorrectSubCommand(subCommandString, subCommand);
        }

        return command;
    }

    public void executeCommand(Command command, CommandMessage commandMessage) {
        command.execute(commandMessage);
    }
}
