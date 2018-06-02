package rocks.cleanstone.game.command;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import rocks.cleanstone.game.command.parameter.CommandParameter;
import rocks.cleanstone.player.Player;

public class SimpleCommandMessage implements CommandMessage {

    private final CommandSender commandSender;
    private final String fullMessage;
    private final String commandName;
    private final List<String> parameters;
    private final CommandRegistry commandRegistry;
    private int parameterIndex = 0;

    public SimpleCommandMessage(CommandSender commandSender, String fullMessage, String commandName,
                                List<String> parameters, CommandRegistry commandRegistry) {
        this.commandSender = commandSender;
        this.fullMessage = fullMessage;
        this.commandName = commandName;
        this.parameters = parameters;
        this.commandRegistry = commandRegistry;
    }

    @Override
    public CommandSender getCommandSender() {
        return commandSender;
    }

    @Override
    public String getFullMessage() {
        return fullMessage;
    }

    @Override
    public String getCommandName() {
        return commandName;
    }

    @Override
    public List<String> getParameters() {
        return parameters;
    }

    @Override
    public int getParameterIndex() {
        return parameterIndex;
    }

    @Override
    public void setParameterIndex(int parameterIndex) {
        this.parameterIndex = parameterIndex;
    }

    private String getNextParameter() {
        return parameters.size() <= parameterIndex ? null : parameters.get(parameterIndex);
    }

    @Override
    public <T> T requireParameter(Class<T> parameterClass) {
        String nextParameter = getNextParameter();
        if (nextParameter == null) {
            throw new NotEnoughParametersException(parameters.size(), parameterIndex + 1);
        }
        T result = getParameter(parameterClass);
        if (result == null) {
            throw new InvalidParameterException(nextParameter, parameterClass);
        }
        parameterIndex++;
        return result;
    }

    @Override
    public Player requireTargetPlayer() {
        if (isParameterPresent(Player.class)) {
            return requireParameter(Player.class);
        } else if (getCommandSender() instanceof Player) {
            return (Player) getCommandSender();
        } else {
            return requireParameter(Player.class);
        }
    }

    @Override
    public String requireStringMessage(boolean optional) {
        return Joiner.on(' ').join(requireVarargParameter(String.class, optional));
    }

    @Override
    public <T> Collection<T> requireVarargParameter(Class<T> parameterClass, boolean allowEmpty) {
        Collection<T> collection = new ArrayList<>();
        while (isParameterPresent(parameterClass)) {
            collection.add(requireParameter(parameterClass));
        }

        if (collection.isEmpty() && !allowEmpty) {
            requireParameter(parameterClass);
        }
        return collection;
    }

    @Override
    public boolean isParameterPresent(Class<?> parameterClass) {
        return getParameter(parameterClass) != null;
    }

    private <T> T getParameter(Class<T> parameterClass) {
        if (getNextParameter() == null) return null;
        CommandParameter<? extends T> commandParameter = commandRegistry.getCommandParameter(parameterClass);
        Preconditions.checkNotNull(commandParameter,
                "Cannot resolve specified parameter class '" + parameterClass.getSimpleName()
                        + "'; you need to register it in the CommandRegistry first");
        return commandParameter.get(getNextParameter());
    }
}
