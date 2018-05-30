package rocks.cleanstone.game.command.listener;

import org.springframework.context.event.EventListener;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.chat.event.PlayerIssuedCommandEvent;
import rocks.cleanstone.game.command.Command;
import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.CommandMessageFactory;
import rocks.cleanstone.game.command.CommandRegistry;

public class CommandListener {

    private final CommandRegistry commandRegistry;

    public CommandListener(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

    @EventListener
    public void onCommand(PlayerIssuedCommandEvent event) {
        CommandMessage commandMessage = CommandMessageFactory
                .construct(event.getPlayer(), event.getCommand(), commandRegistry);
        Command command = commandRegistry.getCommand(commandMessage.getCommandName());

        if (command == null) {
            event.getPlayer().sendMessage(CleanstoneServer.getMessage(
                    "game.command.command-not-found", commandMessage.getCommandName()));
            return;
        }

        commandRegistry.executeCommand(command, commandMessage);
    }
}
