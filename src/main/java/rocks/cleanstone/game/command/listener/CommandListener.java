package rocks.cleanstone.game.command.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;

import rocks.cleanstone.game.chat.ConsoleSender;
import rocks.cleanstone.game.chat.event.PlayerIssuedCommandEvent;
import rocks.cleanstone.game.command.CommandRegistry;

public class CommandListener {

    private final CommandRegistry commandRegistry;

    @Autowired
    public CommandListener(CommandRegistry commandRegistry, ConsoleSender consoleSender) {
        this.commandRegistry = commandRegistry;
        consoleSender.setCommandRegistry(commandRegistry);
    }

    @EventListener
    public void onCommand(PlayerIssuedCommandEvent event) {
        commandRegistry.executeCommand(event.getCommand(), event.getPlayer());
    }
}
