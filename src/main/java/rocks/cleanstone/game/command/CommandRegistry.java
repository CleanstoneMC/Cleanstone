package rocks.cleanstone.game.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import rocks.cleanstone.game.command.commands.SetCommand;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class CommandRegistry {

    private final Map<String, Command> commandMap = new HashMap<>();

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public CommandRegistry() {
        registerCommand(new SetCommand(), false);
    }

    public void registerCommand(Command command, boolean force) {
        if (commandMap.containsKey(command.getName()) && !force) {
            //TODO: Do not register! Throw exception
            return;
        }

        commandMap.put(command.getName(), command);

        if (command.getAliases().size() != 0) {
            for (String alias : command.getAliases()) {
                if (commandMap.containsKey(alias)) {
                    continue; // We dont want to override Commands with aliases
                }

                //TODO: Maybe Override Aliases?

                commandMap.put(alias, command);
            }
        }
    }

    public Command getCommandByName(String command) {
        return commandMap.get(command);
    }

    /**
     * Please execute Commands with this and dont call execute it directly
     * @param command The Command
     * @param commandMessage The Command message
     */
    @Async("commandExec")
    public void executeCommand(Command command, CommandMessage commandMessage) {
        command.execute(commandMessage);
    }
}
