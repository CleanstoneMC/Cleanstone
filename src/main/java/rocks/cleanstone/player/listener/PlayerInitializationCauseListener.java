package rocks.cleanstone.player.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.minecraft.login.event.AsyncLoginSuccessEvent;
import rocks.cleanstone.net.packet.outbound.DisconnectPacket;
import rocks.cleanstone.player.OnlinePlayer;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.game.Identity;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.player.event.AsyncPlayerLoginEvent;

public class PlayerInitializationCauseListener {

    private final PlayerManager playerManager;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public PlayerInitializationCauseListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Async(value = "playerExec")
    @EventListener
    public synchronized void onPlayerLoginSuccess(AsyncLoginSuccessEvent loginEvent) {
        Connection connection = loginEvent.getConnection();
        Identity playerID = playerManager.getPlayerID(loginEvent.getUUID(), loginEvent.getName());

        Player alreadyOnlinePlayer = playerManager.getOnlinePlayer(playerID);
        if (alreadyOnlinePlayer != null) {
            alreadyOnlinePlayer.kick(Text.of(CleanstoneServer.getMessage(
                    "player.logged-in-from-another-location")));
            playerManager.terminatePlayer(alreadyOnlinePlayer);
        }

        AsyncPlayerLoginEvent playerEvent = CleanstoneServer.publishEvent(
                new AsyncPlayerLoginEvent(connection, playerID, loginEvent.getUserProperties()));
        if (playerEvent.isCancelled()) {
            connection.close(new DisconnectPacket(playerEvent.getKickReason()));
            return;
        }
        OnlinePlayer player = new OnlinePlayer(playerID, connection, loginEvent.getUserProperties());

        if (playerManager.isPlayerOperator(playerID)) {
            player.setOp(true);
        }

        playerManager.initializePlayer(player);
    }
}
