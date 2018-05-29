package rocks.cleanstone.game.command;

import rocks.cleanstone.game.permission.Permission;

import java.util.Collection;
import java.util.Map;

public interface Command {
    String getName();

    Collection<String> getAliases();

    boolean allowsPlayer();

    boolean allowsConsole();

    boolean allowsSender(CommandSender sender);

    Permission getPermission();

    Map<String, Command> getSubCommands();

    Command addSubCommand(Command subCommand, String... names);

    boolean showInHelp();

    void execute(CommandMessage commandMessage);
}
