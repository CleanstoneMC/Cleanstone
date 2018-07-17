package rocks.cleanstone.game.command;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Nullable;

import rocks.cleanstone.game.command.parameter.CommandParameter;

public interface CommandRegistry {
    boolean registerCommand(Command command, boolean force);

    boolean registerCommand(Command command);

    void unregisterCommand(Command command);

    @Nullable
    Command getCommand(String command);

    Collection<Command> getAllCommands();

    void registerCommandParameter(CommandParameter commandParameter);

    @Nullable
    <T> CommandParameter<T> getCommandParameter(Class<? super T> parameterClass);

    Set<CommandParameter<?>> getCommandParameters();

    void executeCommand(Command command, CommandMessage commandMessage);

    void executeCommand(String commandLine, CommandSender sender);
}
