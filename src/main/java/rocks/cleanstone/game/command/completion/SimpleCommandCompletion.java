package rocks.cleanstone.game.command.completion;

import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.List;
import static java.util.stream.Collectors.toList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.command.Command;
import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.CommandMessageFactory;
import rocks.cleanstone.game.command.CommandRegistry;
import rocks.cleanstone.net.minecraft.packet.outbound.OutTabCompletePacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.utils.Vector;

@Component
public class SimpleCommandCompletion implements CommandCompletion {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private CommandRegistry commandRegistry;
    private final CommandArgumentsCompletion commandArgumentsCompletion;
    private final MainCommandCompletion mainCommandCompletion;

    @Autowired
    public SimpleCommandCompletion(
            CommandRegistry commandRegistry,
            CommandArgumentsCompletion commandArgumentsCompletion,
            MainCommandCompletion mainCommandCompletion
    ) {
        this.commandRegistry = commandRegistry;
        this.commandArgumentsCompletion = commandArgumentsCompletion;
        this.mainCommandCompletion = mainCommandCompletion;
    }

    @Async
    @Override
    public void completeCommandLine(int transactionId, String commandLine, Player sender, Vector lookedAtBlock) {
        Preconditions.checkNotNull(commandLine, "commandLine cannot be null");
        Preconditions.checkNotNull(sender, "sender cannot be null");

        CommandMessage commandMessage = CommandMessageFactory.construct(sender, commandLine, commandRegistry);
        Command command = commandRegistry.getCommand(commandMessage.getCommandName());

        List<CompletionMatch> matches;
        int start;
        int length;

        if (command == null || shouldCompleteEndingSpace(commandLine, commandMessage)) {
            matches = mainCommandCompletion.completeCommand(commandMessage.getCommandName(), commandRegistry.getAllCommands()) .stream()
                    .map(cm -> cm.withMatch("/" + cm.getMatch())) // prepend slash
                    .collect(toList());
            start = 0;
            length = commandLine.length();
        } else {
            command = resolveSubCommands(command, commandMessage);

            matches = commandArgumentsCompletion.completeArguments(command, commandMessage);
            start = commandLine.endsWith(" ") ? commandLine.length() : commandLine.lastIndexOf(" ") + 1;
            length = commandLine.endsWith(" ") ? 0 : commandLine.length() - start;
        }

        if (matches.size() == 1) {
            CompletionMatch cm = matches.get(0);
            matches = Collections.singletonList(cm.withMatch(cm.getMatch() + " ")); // append space
        }

        sender.sendPacket(new OutTabCompletePacket(transactionId, start, length, matches));
    }

    private boolean shouldCompleteEndingSpace(String commandLine, CommandMessage commandMessage) {
        return commandMessage.getParameters().size() == 0 && !commandLine.endsWith(" ");
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
