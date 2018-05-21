package rocks.cleanstone.game.command;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import rocks.cleanstone.game.command.tabcompleter.TabCompleter;
import rocks.cleanstone.game.permission.Permission;

public interface Command {
    String getName();

    boolean allowsPlayer();

    boolean allowsConsole();

    boolean allowsSender(CommandSender sender);

    Permission getCommandPermission();

    Map<String, Command> getSubCommands();

    boolean generatesTabCompletion();

    boolean showInHelp();

    int getMinimumParameters();

    /**
     * The Classes for the Parameter
     * @return an Array
     */
    Class[] getParameterTypes();

    /**
     * If it returns null a Help Page will be generated if not disabled
     * @return String|null
     */
    String getHelpPage();

    List<String> getAliases();

    void execute(CommandMessage commandMessage);
}
