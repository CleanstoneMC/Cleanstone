package rocks.cleanstone.game.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rocks.cleanstone.game.permission.Permission;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public abstract class SimpleCommand implements Command {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Permission getMinimalPermission() {
        return null;
    }

    @Nullable
    @Override
    public Map<String, SubCommand> getSubCommands() {
        return null;
    }

    @Override
    public boolean generateTabCompletion() {
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
    public boolean allowPlayer() {
        return true;
    }

    @Override
    public boolean allowConsole() {
        return true;
    }

    @Nullable
    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public int neededParameter() {
        return 0;
    }
}
