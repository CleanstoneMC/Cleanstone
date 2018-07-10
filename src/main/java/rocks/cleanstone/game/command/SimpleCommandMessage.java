package rocks.cleanstone.game.command;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import rocks.cleanstone.game.command.completion.CompletionContext;
import rocks.cleanstone.game.command.completion.SimpleCompletionContext;
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
    public <T> Optional<T> optionalParameter(Class<T> parameterClass) {
        String nextParameter = getNextParameter();
        if (nextParameter == null) {
            return Optional.empty();
        }
        T result = getParameter(parameterClass);
        if (result == null) {
            throw new InvalidParameterException(nextParameter, parameterClass);
        }
        parameterIndex++;
        return Optional.of(result);
    }

    @Override
    public <T> T requireParameter(Class<T> parameterClass) {
        return optionalParameter(parameterClass)
                .orElseThrow(() -> new NotEnoughParametersException(parameters.size(), parameterIndex + 1));
    }

    @Override
    public Player requireTargetPlayer() {
        return optionalParameter(Player.class)
                .orElseGet(() -> {
                    if (commandSender instanceof Player) {
                        return (Player) commandSender;
                    } else {
                        throw new NoValidTargetException(commandSender);
                    }
                });
    }

    @Override
    public String requireStringMessage(boolean optional) {
        return requireVarargParameter(String.class, optional).stream()
                .collect(Collectors.joining(" "));
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
        if (getNextParameter() == null) {
            return null;
        }
        CommandParameter<T> commandParameter = commandRegistry.getCommandParameter(parameterClass);
        Preconditions.checkNotNull(commandParameter,
                "Cannot resolve specified parameter class '" + parameterClass.getSimpleName()
                        + "'; you need to register it in the CommandRegistry first");
        CompletionContext<T> context = new SimpleCompletionContext<>(getNextParameter(), parameterClass);
        return commandParameter.get(context);
    }
}
