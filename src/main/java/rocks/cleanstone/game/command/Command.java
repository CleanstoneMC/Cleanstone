package rocks.cleanstone.game.command;

import rocks.cleanstone.game.permission.Permission;

import java.util.List;
import java.util.Map;

public interface Command {
    String getCommandString();

    boolean requiresPlayer();

    boolean allowConsole();

    Permission getMinimalPermission();

    boolean hasSubCommands();

    Map<String, SubCommand> getSubCommands();

    boolean generateTabCompletion();

    boolean showInHelp();

    /**
     * If it returns null a Help Page will be generated if not disabled
     * @return String|null
     */
    String getHelpPage();

    List<String> getAliases();

    void execute(IssuedCommand issuedCommand);
}
