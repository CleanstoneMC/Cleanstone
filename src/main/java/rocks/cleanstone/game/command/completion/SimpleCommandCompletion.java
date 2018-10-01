package rocks.cleanstone.game.command.completion;

import com.google.common.base.Preconditions;
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

import java.util.List;

@Component
public class SimpleCommandCompletion implements CommandCompletion {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private CommandRegistry commandRegistry;
    private final CommandArgumentsCompletion commandArgumentsCompletion;
    private final MainCommandCompletion mainCommandCompletion;

    @Autowired
    public SimpleCommandCompletion(CommandRegistry commandRegistry,
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

        if (command == null || usedByAlias(command, commandMessage)) {
            // completion of parameter without knowing command is not possible
            if (commandMessage.getFullMessage().contains(" ")) {
                return;
            }

            List<CompletionMatch> matches = mainCommandCompletion.completeCommand(commandLine, commandRegistry.getAllCommands());
            sender.sendPacket(new OutTabCompletePacket(transactionId, 0, commandLine.length(), matches));
            return;
        } else {
            command = resolveSubCommands(command, commandMessage);
        }

        int start = commandLine.endsWith(" ") ? commandLine.length() : commandLine.lastIndexOf(" ") + 1;
        int length = commandLine.endsWith(" ") ? 0 : commandLine.length() - start;
        List<CompletionMatch> matches = commandArgumentsCompletion.completeArguments(command, commandMessage);
        sender.sendPacket(new OutTabCompletePacket(transactionId, start, length, matches));
    }

    private boolean usedByAlias(Command command, CommandMessage commandMessage) {
        return !command.getName().equals(commandMessage.getCommandName()) // real command name does not match input
                && !commandMessage.getFullMessage().contains(" "); // still completing command name not a parameter
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
