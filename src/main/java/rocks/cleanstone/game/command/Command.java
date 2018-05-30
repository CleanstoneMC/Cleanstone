package rocks.cleanstone.game.command;

import rocks.cleanstone.game.permission.SecuredAction;

import java.util.Collection;
import java.util.Map;

public interface Command extends SecuredAction {
    String getName();

    Collection<String> getAliases();

    boolean allowsPlayer();

    boolean allowsConsole();

    boolean allowsSender(CommandSender sender);

    Map<String, Command> getSubCommands();

    Command addSubCommand(Command subCommand, String... names);

    boolean showInHelp();

    void execute(CommandMessage commandMessage);

    void execute(CommandMessage commandMessage, boolean considerSubCommands);

    Class[] getExpectedParameterTypes();

    Collection<Command> getParents();
}
