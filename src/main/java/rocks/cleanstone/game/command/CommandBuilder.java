package rocks.cleanstone.game.command;

import com.google.common.base.Preconditions;
import rocks.cleanstone.game.command.executor.CommandExecutor;

import java.util.*;

public class CommandBuilder {

    private final String commandName;
    private List<String> aliases = Collections.emptyList();
    private Map<String, Command> subCommandMap = new HashMap<>();
    private CommandExecutor executor;
    private Class[] expectedParameterTypes = new Class[0];

    private CommandBuilder(String commandName) {
        this.commandName = commandName;
    }

    public static CommandBuilder withName(String commandName) {
        return new CommandBuilder(commandName);
    }

    public CommandBuilder withAliases(String... aliases) {
        this.aliases = Arrays.asList(aliases);
        return this;
    }

    public CommandBuilder withChild(Command subCommand, String... names) {
        for (String name : names)
            subCommandMap.put(name, subCommand);
        return this;
    }

    public CommandBuilder withExecutor(CommandExecutor executor) {
        this.executor = executor;
        return this;
    }

    public CommandBuilder withParameters(Class... expectedParameterTypes) {
        this.expectedParameterTypes = expectedParameterTypes;
        return this;
    }

    public Command create() {
        Preconditions.checkNotNull(commandName, "Command name cannot be null");
        SimpleCommand command = new SimpleCommand(commandName, aliases, executor, expectedParameterTypes);
        subCommandMap.forEach((name, cmd) -> command.addSubCommand(cmd, name));
        return command;
    }
}