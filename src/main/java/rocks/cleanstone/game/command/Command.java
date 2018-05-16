package rocks.cleanstone.game.command;

import rocks.cleanstone.game.permission.Permission;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public interface Command {
    String getCommandString();

    boolean allowPlayer();

    boolean allowConsole();

    Permission getMinimalPermission();

    @Nullable
    Map<String, SubCommand> getSubCommands();

    boolean generateTabCompletion();

    boolean showInHelp();

    int neededParameter();

    /**
     * If it returns null a Help Page will be generated if not disabled
     * @return String|null
     */
    String getHelpPage();

    @Nullable
    List<String> getAliases();

    void execute(IssuedCommand issuedCommand);
}
