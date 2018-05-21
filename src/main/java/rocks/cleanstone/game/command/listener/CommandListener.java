package rocks.cleanstone.game.command.listener;

import org.springframework.context.event.EventListener;

import rocks.cleanstone.game.chat.event.PlayerIssuedCommandEvent;
import rocks.cleanstone.game.command.*;

public class CommandListener {

    private final CommandRegistry commandRegistry;
    private final CommandParser commandParser;

    public CommandListener(CommandRegistry commandRegistry, CommandParser commandParser) {
        this.commandRegistry = commandRegistry;
        this.commandParser = commandParser;
    }

    @EventListener
    public void onCommand(PlayerIssuedCommandEvent playerIssuedCommandEvent) {
        CommandMessage commandMessage = commandParser.getCommandMessageFromMessage(playerIssuedCommandEvent.getPlayer(), playerIssuedCommandEvent.getCommand());

        if (commandMessage == null) {
            //TODO: Command not found
            return;
        }

        Command command = commandParser.getCommandFromString(commandMessage.getCommandName());

        if (command == null) {
            //TODO: Weird Command not found
            return;
        }

        commandRegistry.executeCommand(command, commandMessage);
    }
}
