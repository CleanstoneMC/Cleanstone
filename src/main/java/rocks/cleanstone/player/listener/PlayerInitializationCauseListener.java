package rocks.cleanstone.player.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.Identity;
import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.minecraft.login.event.AsyncLoginSuccessEvent;
import rocks.cleanstone.net.minecraft.packet.outbound.DisconnectPacket;
import rocks.cleanstone.player.OnlinePlayer;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.player.event.AsyncPlayerLoginEvent;

@Component
public class PlayerInitializationCauseListener {

    private final PlayerManager playerManager;

    @Autowired
    public PlayerInitializationCauseListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Async(value = "playerExec")
    @EventListener
    public synchronized void onPlayerLoginSuccess(AsyncLoginSuccessEvent loginEvent) {
        final Connection connection = loginEvent.getConnection();
        final Identity playerID = playerManager.getPlayerID(loginEvent.getUuid(), loginEvent.getName());

        final Player alreadyOnlinePlayer = playerManager.getOnlinePlayer(playerID);
        if (alreadyOnlinePlayer != null) {
            alreadyOnlinePlayer.kick(Text.of(CleanstoneServer.getMessage(
                    "player.logged-in-from-another-location")));
            playerManager.terminatePlayer(alreadyOnlinePlayer);
        }

        final AsyncPlayerLoginEvent playerEvent = CleanstoneServer.publishEvent(
                new AsyncPlayerLoginEvent(connection, playerID, loginEvent.getUserProperties()));
        if (playerEvent.isCancelled()) {
            connection.close(new DisconnectPacket(playerEvent.getKickReason()));
            return;
        }
        final OnlinePlayer player = new OnlinePlayer(playerID, connection, loginEvent.getUserProperties());

        if (playerManager.isPlayerOperator(playerID)) {
            player.setOp(true);
        }

        playerManager.initializePlayer(player);
    }
}
