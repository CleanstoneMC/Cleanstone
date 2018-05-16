package rocks.cleanstone.game.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rocks.cleanstone.game.permission.Permission;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public abstract class SimpleSubCommand implements SubCommand {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean allowPlayer() {
        return true;
    }

    @Override
    public boolean allowConsole() {
        return true;
    }

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
    public int neededParameter() {
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
