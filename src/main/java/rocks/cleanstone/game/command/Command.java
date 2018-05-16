package rocks.cleanstone.game.command;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import rocks.cleanstone.game.permission.Permission;

public interface Command {
    String getName();

    boolean allowsPlayer();

    boolean allowsConsole();

    boolean allowsSender(CommandSender sender);

    Permission getCommandPermission();

    @Nullable
    Map<String, SubCommand> getSubCommands();

    boolean generatesTabCompletion();

    boolean showInHelp();

    int getMinimumParameters();

    /**
     * If it returns null a Help Page will be generated if not disabled
     * @return String|null
     */
    String getHelpPage();

    @Nullable
    List<String> getAliases();

    void execute(CommandMessage commandMessage);
}
