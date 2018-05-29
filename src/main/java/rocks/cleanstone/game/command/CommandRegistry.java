package rocks.cleanstone.game.command;

import rocks.cleanstone.game.command.parameter.CommandParameter;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Set;

public interface CommandRegistry {
    void registerCommand(Command command, boolean force);

    void unregisterCommand(Command command);

    Command getCommand(String command);

    Collection<Command> getAllCommands();

    void registerCommandParameter(CommandParameter commandParameter);

    @Nullable
    <T> CommandParameter<? extends T> getCommandParameter(Class<? extends T> parameterClass);

    Set<CommandParameter<?>> getCommandParameters();

    void executeCommand(Command command, CommandMessage commandMessage);
}
