package rocks.cleanstone.logic.command.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.chat.event.PlayerIssuedCommandEvent;
import rocks.cleanstone.game.command.CommandRegistry;

@Component
public class CommandListener {

    private final CommandRegistry commandRegistry;

    @Autowired
    public CommandListener(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

    @EventListener
    public void onCommand(PlayerIssuedCommandEvent event) {
        commandRegistry.executeCommand(event.getCommand(), event.getPlayer());
    }
}
