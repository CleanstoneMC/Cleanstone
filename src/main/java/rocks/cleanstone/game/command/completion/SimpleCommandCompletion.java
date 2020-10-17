package rocks.cleanstone.game.command.completion;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.command.*;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.stream.Collectors.toList;

@Slf4j
@Component
public class SimpleCommandCompletion implements CommandCompletion {
    private final CommandRegistry commandRegistry;
    private final MainCommandCompletion mainCommandCompletion;
    private final CommandArgumentsCompletion argumentsCompletion;

    @Autowired
    public SimpleCommandCompletion(
            CommandRegistry commandRegistry,
            MainCommandCompletion mainCommandCompletion,
            CommandArgumentsCompletion argumentsCompletion
    ) {
        this.commandRegistry = commandRegistry;
        this.mainCommandCompletion = mainCommandCompletion;
        this.argumentsCompletion = argumentsCompletion;
    }

    @Async
    @Override
    public CompletableFuture<List<String>> completeCommandLine(String commandLine, CommandSender sender) {
        Preconditions.checkNotNull(commandLine, "commandLine cannot be null");
        Preconditions.checkNotNull(sender, "sender cannot be null");

        CommandMessage commandMessage = CommandMessageFactory.construct(sender, commandLine.toLowerCase(), commandRegistry);
        Command command = commandRegistry.getCommand(commandMessage.getCommandName());
        List<String> matches;

        if (command == null) {
            matches = mainCommandCompletion.completeCommand(
                    commandMessage.getCommandName(),
                    commandRegistry.getAllCommands()
            ).stream()
                    .map(completion -> "/" + completion)
                    .collect(toList());
        } else {
            command = resolveSubCommands(command, commandMessage);

            matches = argumentsCompletion.completeArguments(command, commandMessage);
        }

        if (matches.size() == 1) {
            // add space if we know which command was meant
            matches = Collections.singletonList(matches.get(0) + " ");
        }

        return CompletableFuture.completedFuture(matches);
    }

    private Command resolveSubCommands(Command command, CommandMessage commandMessage) {
        while (commandMessage.nextParameterIs(String.class)) {
            String parameter = commandMessage.requireParameter(String.class);
            Command subCommand = command.getSubCommands().get(parameter);
            if (subCommand != null) {
                command = subCommand;
            } else {
                commandMessage.setParameterIndex(commandMessage.getParameterIndex() - 1);
                break;
            }
        }

        return command;
    }

}
