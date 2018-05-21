package rocks.cleanstone.game.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import rocks.cleanstone.game.chat.Console;
import rocks.cleanstone.game.permission.Permission;
import rocks.cleanstone.player.Player;

public abstract class SimpleCommand implements Command {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private final String name;
    private final List<String> aliases;
    private final Map<String, Command> subCommandMap = new HashMap<>();

    public SimpleCommand(String name, Collection<String> aliases) {
        this.name = name;
        this.aliases = new ArrayList<>(aliases);
    }

    public SimpleCommand(String name) {
        this(name, new ArrayList<>());
    }

    @Override
    public Permission getCommandPermission() {
        return null;
    }

    @Override
    public Map<String, Command> getSubCommands() {
        return subCommandMap;
    }

    @Override
    public boolean generatesTabCompletion() {
        return true;
    }

    @Override
    public boolean showInHelp() {
        return true;
    }

    @Override
    public String getHelpPage() {
        return null;
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
    }

    @Override
    public int getMinimumParameters() {
        return 0;
    }

    @Override
    public Class[] getParameterTypes() {
        return new Class[0];
    }
}
