package rocks.cleanstone.game.command.completion;

import com.google.common.base.Preconditions;

import org.springframework.scheduling.annotation.Async;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import rocks.cleanstone.game.command.Command;
import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.CommandMessageFactory;
import rocks.cleanstone.game.command.CommandRegistry;
import rocks.cleanstone.game.command.parameter.CommandParameter;
import rocks.cleanstone.net.packet.outbound.OutTabCompletePacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.utils.Vector;

public class SimpleCommandCompletion implements CommandCompletion {
    private CommandRegistry commandRegistry;

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

        if (command == null) {
            // completion of parameter without knowing command
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

    private List<String> completeCommand(String commandPart) {
        List<Command> matchingCommands = commandRegistry.getAllCommands().stream()
                .filter(current -> current.getName().toLowerCase(Locale.ENGLISH)
                        .startsWith(commandPart.toLowerCase(Locale.ENGLISH)))
                .collect(Collectors.toList());

        boolean multipleMatches = matchingCommands.size() > 1;

        return matchingCommands.stream()
                .map(command -> getCommandCompletionString(command, multipleMatches))
                .collect(Collectors.toList());
    }

    private String getCommandCompletionString(Command command, boolean multipleMatches) {
        String suffix = multipleMatches || command.getExpectedParameterTypes().length == 0 ? "" : " ";
        return "/" + command.getName() + suffix;
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
