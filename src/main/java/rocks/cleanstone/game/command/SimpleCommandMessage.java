package rocks.cleanstone.game.command;

import com.google.common.base.Preconditions;
import rocks.cleanstone.game.command.parameter.CommandParameter;
import rocks.cleanstone.player.Player;

import javax.annotation.Nullable;
import java.util.List;

public class SimpleCommandMessage implements CommandMessage {

    private final CommandSender commandSender;
    private final String fullCommand;
    private final String commandName;
    private final List<String> parameters;
    private final CommandRegistry commandRegistry;
    private int parameterIndex = 0;

    public SimpleCommandMessage(CommandSender commandSender, String fullCommand, String commandName,
                                List<String> parameters, CommandRegistry commandRegistry) {
        this.commandSender = commandSender;
        this.fullCommand = fullCommand;
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
        return fullCommand;
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
    @Nullable
    public String getNextParameter() {
        return parameters.size() <= parameterIndex ? null : parameters.get(parameterIndex);
    }

    @Override
    public void setParameterIndex(int parameterIndex) {
        this.parameterIndex = parameterIndex;
    }

    @Override
    public int getParameterIndex() {
        return parameterIndex;
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
