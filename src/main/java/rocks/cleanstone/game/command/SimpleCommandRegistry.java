package rocks.cleanstone.game.command;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import rocks.cleanstone.game.command.cleanstone.GameModeCommand;
import rocks.cleanstone.game.command.cleanstone.SetCommand;
import rocks.cleanstone.game.command.executor.HelpPageExecutor;
import rocks.cleanstone.game.command.parameter.*;
import rocks.cleanstone.player.PlayerManager;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class SimpleCommandRegistry implements CommandRegistry {

    private final Map<String, Command> commandMap = Maps.newConcurrentMap();

    private final Set<CommandParameter<?>> commandParameters = Sets.newConcurrentHashSet();

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public SimpleCommandRegistry(PlayerManager playerManager) {
        registerCommand(new SetCommand(), false);
        registerCommand(new GameModeCommand(), false);
        registerCommandParameter(new StringParameter());
        registerCommandParameter(new IntParameter());
        registerCommandParameter(new PlayerParameter(playerManager));
        registerCommandParameter(new GameModeParameter());
    }

    @Override
    public void registerCommand(Command command, boolean force) {
        if (commandMap.containsKey(command.getName()) && !force) {
            //TODO: Do not register! Throw exception
            return;
        }

        commandMap.put(command.getName(), command);

        if (command.getAliases().size() != 0) {
            for (String alias : command.getAliases()) {
                if (commandMap.containsKey(alias)) {
                    continue; // We dont want to override Commands with aliases
                }

                //TODO: Maybe Override Aliases?

                commandMap.put(alias, command);
            }
        }
    }

    @Override
    public void unregisterCommand(Command command) {
        commandMap.values().removeIf(cmd -> cmd.equals(command));
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
    public <T> CommandParameter<? extends T> getCommandParameter(Class<? extends T> parameterClass) {
        //noinspection unchecked
        return (CommandParameter<? extends T>) commandParameters.stream().filter(
                parameter -> parameterClass.isAssignableFrom(parameter.getParameterClass())).findFirst().orElse(null);
    }

    @Override
    public Set<CommandParameter<?>> getCommandParameters() {
        return commandParameters;
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
            command.execute(commandMessage);
        } catch (InvalidParameterException | NotEnoughParametersException e) {
            new HelpPageExecutor(command).execute(commandMessage);
        }
    }
}
