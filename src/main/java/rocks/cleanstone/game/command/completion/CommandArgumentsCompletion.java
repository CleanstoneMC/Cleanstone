package rocks.cleanstone.game.command.completion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import rocks.cleanstone.game.command.Command;
import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.CommandRegistry;
import rocks.cleanstone.game.command.parameter.CommandParameter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CommandArgumentsCompletion {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final CommandRegistry commandRegistry;
    private final MainCommandCompletion mainCommandCompletion;

    public CommandArgumentsCompletion(CommandRegistry commandRegistry, MainCommandCompletion mainCommandCompletion) {
        this.commandRegistry = commandRegistry;
        this.mainCommandCompletion = mainCommandCompletion;
    }

    public List<String> completeArguments(Command command, CommandMessage commandMessage) {
        final List<String> matches = new LinkedList<>();

        getParameterValue(commandMessage).ifPresent(parameterValue -> {
            logger.debug("found paramter value: {}", parameterValue);
            getParameterType(command, commandMessage).ifPresent(parameterType -> {
                logger.debug("inferred parameter type: {}", parameterType);
                matches.addAll(completeParameter(parameterValue, parameterType));
            });

            // complete subcommands
            matches.addAll(mainCommandCompletion.completeCommand(
                    parameterValue,
                    command.getSubCommands().values()
            ));
        });

        return matches;
    }

    private Optional<String> getParameterValue(CommandMessage commandMessage) {
        if (commandMessage.getFullMessage().endsWith(" ")) {
            // complete new parameter
            return Optional.of("");
        } else if (!commandMessage.getParameters().isEmpty()) {
            final List<String> parameters = commandMessage.getParameters();
            return Optional.of(parameters.get(parameters.size() - 1));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Class<?>> getParameterType(Command command, CommandMessage commandMessage) {
        final int currentIndex = commandMessage.getParameterIndex() + 1;
        int totalParameters = commandMessage.getParameters().size();

        // complete new parameter
        if (commandMessage.getFullMessage().endsWith(" ")) {
            totalParameters++;
        }

        // index of parameter as it is registered in a possible subcommand
        final int subCommandIndex = totalParameters - currentIndex;
        final Class<?>[] expectedTypes = command.getExpectedParameterTypes();

        if (subCommandIndex < 0 || subCommandIndex >= expectedTypes.length) {
            return Optional.empty();
        }

        return Optional.of(expectedTypes[subCommandIndex]);
    }

    private <T> List<String> completeParameter(String parameterValue, Class<T> parameterType) {
        final CommandParameter<T> commandParameter = commandRegistry.getCommandParameter(parameterType);

        // check if parameter is completable
        if (!(commandParameter instanceof CompletableParameter)) {
            return Collections.emptyList();
        }

        final CompletionContext<T> context = new SimpleCompletionContext<>(parameterValue, parameterType);
        return ((CompletableParameter<T>) commandParameter).getCompletion(context);
    }
}
