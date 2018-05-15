package rocks.cleanstone.player;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import rocks.cleanstone.Cleanstone;
import rocks.cleanstone.core.CleanstoneMainServer;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.minecraft.login.event.AsyncLoginSuccessEvent;
import rocks.cleanstone.net.minecraft.packet.data.Text;
import rocks.cleanstone.net.minecraft.packet.outbound.DisconnectPacket;
import rocks.cleanstone.player.event.AsyncPlayerLoginEvent;

public class PlayerInitializationCauseListener {

    private final PlayerManager playerManager;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public PlayerInitializationCauseListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Async(value = "playerExec")
    @EventListener
    public void onPlayerLoginSuccess(AsyncLoginSuccessEvent loginEvent) {
        Connection connection = loginEvent.getConnection();
        PlayerID playerID = playerManager.getPlayerID(loginEvent.getUUID(), loginEvent.getName());

        Player alreadyOnlinePlayer = playerManager.getOnlinePlayer(playerID);
        if (alreadyOnlinePlayer != null) {
            alreadyOnlinePlayer.kick(Text.of(CleanstoneServer.getMessage(
                    "core.player.logged-in-from-another-location")));
            playerManager.terminatePlayer(alreadyOnlinePlayer);
        }

        AsyncPlayerLoginEvent playerEvent = CleanstoneServer.publishEvent(
                new AsyncPlayerLoginEvent(connection, playerID));
        if (playerEvent.isCancelled()) {
            connection.close(new DisconnectPacket(playerEvent.getKickReason()));
            return;
        }
        OnlinePlayer player = new OnlinePlayer(playerID, connection);

        if (playerManager.isPlayerOperator(playerID)) {
            player.setOp(true);
        }

        playerManager.initializePlayer(player);
    }
}
