package rocks.cleanstone.game.command.listener;

import org.springframework.context.event.EventListener;
import rocks.cleanstone.game.chat.event.PlayerIssuedCommandEvent;
import rocks.cleanstone.game.command.CommandRegistry;
import rocks.cleanstone.game.command.IssuedCommand;
import rocks.cleanstone.game.command.SimpleIssuedCommand;

public class CommandListener {
    private final CommandRegistry commandRegistry;

    public CommandListener(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

    @EventListener
    public void onCommand(PlayerIssuedCommandEvent playerIssuedCommandEvent) {
        IssuedCommand issuedCommand = new SimpleIssuedCommand(playerIssuedCommandEvent.getPlayer(), playerIssuedCommandEvent.getCommand());

        commandRegistry.executeCommand(playerIssuedCommandEvent.getCommand(), issuedCommand);
    }
}
