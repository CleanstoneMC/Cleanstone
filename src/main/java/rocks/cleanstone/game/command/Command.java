package rocks.cleanstone.game.command;

import rocks.cleanstone.game.permission.Permission;

import java.util.List;

public interface Command {
    String getCommandString();

    boolean requiresPlayer();

    Permission getMinimalPermission();

    boolean hasSubCommands();

    List<SubCommand> getSubCommands();

    boolean generateTabCompletion();

    boolean showInHelp();

    /**
     * If it returns null a Help Page will be generated if not disabled
     * @return String|null
     */
    String getHelpPage();

}
