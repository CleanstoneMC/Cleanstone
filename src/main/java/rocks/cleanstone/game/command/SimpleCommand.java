package rocks.cleanstone.game.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rocks.cleanstone.game.permission.Permission;

import java.util.List;

public abstract class SimpleCommand implements Command {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public Permission getMinimalPermission() {
        return null;
    }

    @Override
    public boolean hasSubCommands() {
        return false;
    }

    @Override
    public List<SubCommand> getSubCommands() {
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
}
