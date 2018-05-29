package rocks.cleanstone.game.command;

import com.google.common.base.Preconditions;

import java.util.*;

public class CommandBuilder {

    private String name;
    private List<String> aliases = Collections.emptyList();
    private Map<String, Command> subCommandMap = new HashMap<>();

    private CommandBuilder(String commandName) {
        this.name = commandName;
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

    public Command create() {
        Preconditions.checkNotNull(name, "Command name cannot be null");
        SimpleCommand command = new SimpleCommand(name, aliases);
        command.getSubCommands().putAll(subCommandMap);
        return command;
    }
}