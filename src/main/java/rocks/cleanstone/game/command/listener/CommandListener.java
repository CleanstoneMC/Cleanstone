package rocks.cleanstone.game.command.listener;

import org.springframework.context.event.EventListener;

import rocks.cleanstone.game.chat.event.PlayerIssuedCommandEvent;
import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.CommandRegistry;
import rocks.cleanstone.game.command.SimpleCommandMessage;

public class CommandListener {

    private final CommandRegistry commandRegistry;

    public CommandListener(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

    @EventListener
    public void onCommand(PlayerIssuedCommandEvent playerIssuedCommandEvent) {
        CommandMessage commandMessage = new SimpleCommandMessage(playerIssuedCommandEvent.getPlayer(),
                playerIssuedCommandEvent.getCommand());

        commandRegistry.executeCommand(playerIssuedCommandEvent.getCommand(), commandMessage);
    }
}
