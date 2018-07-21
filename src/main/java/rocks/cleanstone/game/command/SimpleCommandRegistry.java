package rocks.cleanstone.game.command;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.command.executor.InvalidUsageExecutor;
import rocks.cleanstone.game.command.parameter.CommandParameter;

public class SimpleCommandRegistry implements CommandRegistry {

    private final Map<String, Command> commandMap = Maps.newConcurrentMap();

    private final Set<CommandParameter<?>> commandParameters = Sets.newConcurrentHashSet();

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Collection<Command> standardCommands;
    @Autowired
    private Collection<CommandParameter<?>> standardParameters;

    public void init() {
        standardCommands.forEach(this::registerCommand);
        standardParameters.forEach(this::registerCommandParameter);
    }

    @Override
    public boolean registerCommand(Command command, boolean force) {
        Preconditions.checkNotNull(command, "command cannot be null");

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
        if (command == null) {
            return false;
        }
        return command.getName().equalsIgnoreCase(name);
    }

    @Override
    public void unregisterCommand(Command command) {
        commandMap.values().removeIf(cmd -> cmd.equals(command));
        // TODO unregister children?
    }

    @Nullable
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
        Preconditions.checkNotNull(commandParameter, "commandParameter cannot be null");

        commandParameters.add(commandParameter);
    }

    @Override
    @Nullable
    public <T> CommandParameter<T> getCommandParameter(Class<? super T> parameterClass) {
        //noinspection unchecked
        return (CommandParameter<T>) commandParameters.stream().filter(
                parameter -> parameter.getParameterClass().isAssignableFrom(parameterClass)).findFirst().orElse(null);
    }

    @Override
    public Set<CommandParameter<?>> getCommandParameters() {
        return ImmutableSet.copyOf(commandParameters);
    }

    @Async("commandExec")
    @Override
    public void executeCommand(Command command, CommandMessage commandMessage) {
        Preconditions.checkNotNull(command, "command cannot be null");
        Preconditions.checkNotNull(commandMessage, "commandMessage cannot be null");

        try {
            command.execute(commandMessage, true);
        } catch (CommandException e) {
            new InvalidUsageExecutor(command, e).execute(commandMessage);
        } catch (Exception e) {
            throw new CommandException("Error occurred while executing command " + command.getName(), e);
        }
    }

    @Async("commandExec")
    @Override
    public void executeCommand(String commandLine, CommandSender sender) {
        Preconditions.checkNotNull(commandLine, "commandLine cannot be null");
        Preconditions.checkNotNull(sender, "sender cannot be null");

        CommandMessage commandMessage = CommandMessageFactory.construct(sender, commandLine, this);
        Command command = getCommand(commandMessage.getCommandName());

        if (command == null) {
            sender.sendRawMessage(CleanstoneServer.getMessage(
                    "game.command.command-not-found", commandMessage.getCommandName()));
            return;
        }
        executeCommand(command, commandMessage);
    }
}
