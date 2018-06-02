package rocks.cleanstone.game.command;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import rocks.cleanstone.game.command.executor.HelpPageExecutor;
import rocks.cleanstone.game.command.parameter.CommandParameter;
import rocks.cleanstone.player.PlayerManager;

public class SimpleCommandRegistry implements CommandRegistry {

    private final Map<String, Command> commandMap = Maps.newConcurrentMap();

    private final Set<CommandParameter<?>> commandParameters = Sets.newConcurrentHashSet();

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public SimpleCommandRegistry(PlayerManager playerManager, Collection<Command> standardCommands,
                                 Collection<CommandParameter<?>> standardParameters) {
        standardCommands.forEach(this::registerCommand);
        standardParameters.forEach(this::registerCommandParameter);
    }

    @Override
    public boolean registerCommand(Command command, boolean force) {
        String commandName = command.getName().toLowerCase(Locale.ENGLISH);
        if (isRegisteredCommandName(commandName) && !force) {
            return false;
        }
        commandMap.put(commandName, command);

        for (String alias : command.getAliases()) {
            alias = alias.toLowerCase(Locale.ENGLISH);
            if (commandMap.containsKey(alias) && !force) {
                continue;
            } else if (isRegisteredCommandName(alias)) {
                continue;
            }
            commandMap.put(alias, command);
        }
        return true;
    }

    @Override
    public boolean registerCommand(Command command) {
        return registerCommand(command, false);
    }

    private boolean isRegisteredCommandName(String name) {
        Command command = getCommand(name);
        if (command == null) return false;
        return command.getName().equalsIgnoreCase(name);
    }

    @Override
    public void unregisterCommand(Command command) {
        commandMap.values().removeIf(cmd -> cmd.equals(command));
        // TODO unregister children?
    }

    @Override
    public Command getCommand(String command) {
        return commandMap.get(command);
    }

    @Override
    public Collection<Command> getAllCommands() {
        return ImmutableSet.copyOf(commandMap.values());
    }

    @Override
    public void registerCommandParameter(CommandParameter commandParameter) {
        commandParameters.add(commandParameter);
    }

    @Override
    @Nullable
    public <T> CommandParameter<? extends T> getCommandParameter(Class<T> parameterClass) {
        //noinspection unchecked
        return (CommandParameter<? extends T>) commandParameters.stream().filter(
                parameter -> parameterClass.isAssignableFrom(parameter.getParameterClass())).findFirst().orElse(null);
    }

    @Override
    public Set<CommandParameter<?>> getCommandParameters() {
        return ImmutableSet.copyOf(commandParameters);
    }

    /**
     * Please execute Commands with this and dont call execute directly
     *
     * @param command        The Command
     * @param commandMessage The Command message
     */
    @Async("commandExec")
    @Override
    public void executeCommand(Command command, CommandMessage commandMessage) {
        try {
            command.execute(commandMessage, true);
        } catch (InvalidParameterException | NotEnoughParametersException e) {
            new HelpPageExecutor(command).execute(commandMessage);
        } catch (Exception e) {
            throw new CommandException("Error occurred while executing command " + command.getName(), e);
        }
    }
}
