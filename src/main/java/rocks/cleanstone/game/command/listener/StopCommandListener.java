package rocks.cleanstone.game.command.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import rocks.cleanstone.game.chat.event.PlayerIssuedCommandEvent;

public class StopCommandListener {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @EventListener
    @Async("chatExec")
    public void onStop(PlayerIssuedCommandEvent playerIssuedCommandEvent) {
        if (!playerIssuedCommandEvent.getCommand().equals("stop")) {
            return;
        }

        if (!playerIssuedCommandEvent.getPlayer().isOp()) {
            logger.info("Player is not an Operator");
            return;
        }

        System.exit(0);
    }
}
