package rocks.cleanstone.game.command.completion;

import com.google.common.base.Preconditions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import rocks.cleanstone.game.command.Command;
import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.CommandMessageFactory;
import rocks.cleanstone.game.command.CommandRegistry;
import rocks.cleanstone.game.command.parameter.CommandParameter;
import rocks.cleanstone.net.packet.outbound.OutTabCompletePacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.utils.Vector;

public class SimpleCommandCompletion implements CommandCompletion {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private CommandRegistry commandRegistry;

    @Autowired
    public SimpleCommandCompletion(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

    @Async
    @Override
    public void completeCommandLine(String commandLine, Player sender, Vector lookedAtBlock) {
        Preconditions.checkNotNull(commandLine, "commandLine cannot be null");
        Preconditions.checkNotNull(sender, "sender cannot be null");

        CommandMessage commandMessage = CommandMessageFactory.construct(sender, commandLine, commandRegistry);
        Command command = commandRegistry.getCommand(commandMessage.getCommandName());

        if (command == null || usedByAlias(command, commandMessage)) {
            // completion of parameter without knowing command is not possible
            if (commandMessage.getFullMessage().contains(" ")) {
                return;
            }

            List<String> matches = completeCommand(commandMessage.getCommandName());
            sender.sendPacket(new OutTabCompletePacket(matches));
            return;
        }

        List<String> matches = completeParameter(commandMessage, command);
        sender.sendPacket(new OutTabCompletePacket(matches));
    }

    private boolean usedByAlias(Command command, CommandMessage commandMessage) {
        return !command.getName().equals(commandMessage.getCommandName()) // real command name does not match input
                && !commandMessage.getFullMessage().contains(" "); // still completing command name not a parameter
    }

    private List<String> completeCommand(String input) {
        String lowerCaseInput = input.toLowerCase(Locale.ENGLISH);
        List<Command> matchingCommands = commandRegistry.getAllCommands().stream()
                .filter(command -> filteredCommandNames(command, lowerCaseInput).findAny().isPresent()) // command has matching name or alias
                .collect(Collectors.toList());

        boolean multipleMatches = matchingCommands.size() > 1;

        return matchingCommands.stream()
                .map(command -> getCommandCompletionString(command, multipleMatches, lowerCaseInput))
                .collect(Collectors.toList());
    }

    private String getCommandCompletionString(Command command, boolean multipleMatches, String commandPart) {
        String commandName = filteredCommandNames(command, commandPart)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("command has no matching name"));
        // add trailing space if there is only one match and parameters are expected^
        String suffix = !multipleMatches && command.getExpectedParameterTypes().length > 0 ? " " : "";
        return "/" + commandName + suffix;
    }

    private Stream<String> filteredCommandNames(Command command, String filter) {
        return Stream.concat(Stream.of(command.getName()), command.getAliases().stream())
                .map(name -> name.toLowerCase(Locale.ENGLISH))
                .filter(name -> name.startsWith(filter));
    }

    private <T> List<String> completeParameter(CommandMessage commandMessage, Command command) {
        List<String> parameterValues = new LinkedList<>(commandMessage.getParameters());
        int lastParameterIndex = parameterValues.size() - 1;

        // complete next parameter when command ends in space
        if (commandMessage.getFullMessage().endsWith(" ")) {
            parameterValues.add("");
            lastParameterIndex++;
        }

        Class<?>[] expectedParameterTypes = command.getExpectedParameterTypes();

        // not completing any parameter or completing too many parameters
        if (lastParameterIndex < 0 || lastParameterIndex >= expectedParameterTypes.length) {
            return Collections.emptyList();
        }

        //noinspection unchecked
        Class<T> parameterType = (Class<T>) expectedParameterTypes[lastParameterIndex];
        String lastParameterValue = parameterValues.get(lastParameterIndex);

        CommandParameter<T> commandParameter = commandRegistry.getCommandParameter(parameterType);

        // check if parameter is completable
        if (!(commandParameter instanceof CompletableParameter)) {
            return Collections.emptyList();
        }

        CompletionContext<T> context = new SimpleCompletionContext<>(lastParameterValue, parameterType);
        return ((CompletableParameter<T>) commandParameter).getCompletion(context);
    }
}
