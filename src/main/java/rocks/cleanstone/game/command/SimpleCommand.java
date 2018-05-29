package rocks.cleanstone.game.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rocks.cleanstone.game.chat.Console;
import rocks.cleanstone.game.command.executor.CommandExecutor;
import rocks.cleanstone.game.command.executor.SubCommandExecutor;
import rocks.cleanstone.game.permission.Permission;
import rocks.cleanstone.player.Player;

import java.util.*;

public class SimpleCommand implements Command {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private final String name;
    private final List<String> aliases;
    private final Map<String, Command> subCommandMap = new HashMap<>();
    private final CommandExecutor commandExecutor;

    public SimpleCommand(String name, Collection<String> aliases, CommandExecutor commandExecutor) {
        this.name = name;
        this.aliases = new ArrayList<>(aliases);
        this.commandExecutor = commandExecutor;
    }

    public SimpleCommand(String name, CommandExecutor commandExecutor) {
        this(name, new ArrayList<>(), commandExecutor);
    }

    public SimpleCommand(String name, Collection<String> aliases) {
        this(name, aliases, null);
    }

    public SimpleCommand(String name) {
        this(name, new ArrayList<>(), null);
    }

    @Override
    public Permission getPermission() {
        return null;
    }

    @Override
    public Map<String, Command> getSubCommands() {
        return subCommandMap;
    }

    @Override
    public Command addSubCommand(Command subCommand, String... names) {
        for (String name : names)
            getSubCommands().put(name, subCommand);
        return this;
    }

    @Override
    public boolean showInHelp() {
        return true;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean allowsPlayer() {
        return true;
    }

    @Override
    public boolean allowsConsole() {
        return true;
    }

    @Override
    public boolean allowsSender(CommandSender sender) {
        if (!allowsPlayer() && sender instanceof Player) return false;
        return allowsConsole() || !(sender instanceof Console);
    }

    @Override
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public void execute(CommandMessage commandMessage) {
        if (commandExecutor != null) {
            commandExecutor.execute(commandMessage);
        } else {
            new SubCommandExecutor(this).execute(commandMessage);
        }
    }
}