package rocks.cleanstone.game.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import rocks.cleanstone.game.permission.Permission;

public abstract class SimpleSubCommand implements SubCommand {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

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
        return false;
    }

    @Override
    public Permission getCommandPermission() {
        return null;
    }

    @Nullable
    @Override
    public Map<String, SubCommand> getSubCommands() {
        return null;
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
    public int getMinimumParameters() {
        return 0;
    }

    @Override
    public String getHelpPage() {
        return null;
    }

    @Nullable
    @Override
    public List<String> getAliases() {
        return null;
    }

}
