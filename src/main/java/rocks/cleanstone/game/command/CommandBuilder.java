package rocks.cleanstone.game.command;

import com.google.common.base.Preconditions;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandBuilder {

    private String name;
    private List<String> aliases = Collections.emptyList();
    private Map<String, SubCommand> subCommandMap = new HashMap<>();

    public CommandBuilder() {
    }

    public CommandBuilder name(String commandName) {
        this.name = commandName;
        return this;
    }

    public CommandBuilder aliases(String... aliases) {
        this.aliases = Arrays.asList(aliases);
        return this;
    }

    public CommandBuilder child(SubCommand subCommand, String... names) {
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
