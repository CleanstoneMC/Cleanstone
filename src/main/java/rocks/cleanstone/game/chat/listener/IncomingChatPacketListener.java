package rocks.cleanstone.game.chat.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.chat.event.PlayerChatMessageEvent;
import rocks.cleanstone.game.chat.event.PlayerIssuedCommandEvent;
import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.packet.inbound.InChatMessagePacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerID;
import rocks.cleanstone.player.PlayerManager;

public class IncomingChatPacketListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final PlayerManager playerManager;

    public IncomingChatPacketListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Async("chatExec")
    @EventListener
    public void onChatMessage(InboundPacketEvent inboundPacketEvent) {
        if (!(inboundPacketEvent.getPacket() instanceof InChatMessagePacket)) {
            return;
        }

        Player player = playerManager.getOnlinePlayer(inboundPacketEvent.getConnection());
        if (player == null) return;
        PlayerID playerID = player.getId();
        String playerName = playerID.getName() + "(" + playerID.getUUID() + ")";

        InChatMessagePacket chatMessagePacket = ((InChatMessagePacket) inboundPacketEvent.getPacket());
        String chatMessage = chatMessagePacket.getMessage();
        if (chatMessage.isEmpty()) return;
        if (chatMessage.charAt(0) == '/') {
            //Command

            logger.info("Command from {}: {}", playerName, chatMessage);

            CleanstoneServer.publishEvent(new PlayerIssuedCommandEvent(player, chatMessage));
        } else {
            logger.info("Message from {}: {}", playerName, chatMessage);

            CleanstoneServer.publishEvent(new PlayerChatMessageEvent(player, chatMessage));
        }
    }
}
