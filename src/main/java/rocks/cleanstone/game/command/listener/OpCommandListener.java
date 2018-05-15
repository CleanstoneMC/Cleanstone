package rocks.cleanstone.game.command.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import rocks.cleanstone.game.chat.event.PlayerIssuedCommandEvent;
import rocks.cleanstone.game.chat.message.Chat;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

public class OpCommandListener {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final PlayerManager playerManager;

    public OpCommandListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @EventListener
    @Async("chatExec")
    public void onOp(PlayerIssuedCommandEvent playerIssuedCommandEvent) {
        if (!playerIssuedCommandEvent.getCommand().equals("op")) {
            return;
        }

        Player player = check(playerIssuedCommandEvent.getPlayer(), playerIssuedCommandEvent.getParameter()[0]);

        if (player == null) {
            return;
        }

        player.setOp(true);

        player.sendMessage(new Chat("You are now an Operator"));
    }

    @EventListener
    @Async("chatExec")
    public void onDeOp(PlayerIssuedCommandEvent playerIssuedCommandEvent) {
        if (!playerIssuedCommandEvent.getCommand().equals("deop")) {
            return;
        }

        Player player = check(playerIssuedCommandEvent.getPlayer(), playerIssuedCommandEvent.getParameter()[0]);

        if (player == null) {
            return;
        }

        player.setOp(false);

        player.sendMessage(new Chat("You are no longer an Operator"));
    }

    private Player check(Player issuer, String name) {
        if (!issuer.isOp()) {
            logger.info("Player is not an Operator");
            return null;
        }

        Player player = playerManager.getOnlinePlayer(name);

        if (player == null) {
            logger.info("Could not find Player: {}", name);
            return null;
        }

        return player;
    }
}
