package rocks.cleanstone.game.command.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.chat.event.PlayerTabCompleteEvent;
import rocks.cleanstone.game.command.completion.CommandCompletion;

@Component
public class TabCompleteListener {

    private final CommandCompletion commandCompletion;

    @Autowired
    public TabCompleteListener(CommandCompletion commandCompletion) {
        this.commandCompletion = commandCompletion;
    }

    @EventListener
    public void onCommand(PlayerTabCompleteEvent event) {
        commandCompletion.completeCommandLine(event.getTransactionId(), event.getText(), event.getPlayer(), event.getLookedAtBlock());
    }
}
