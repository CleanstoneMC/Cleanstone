package rocks.cleanstone.game.chat.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import java.util.Arrays;

import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.chat.event.PlayerChatMessageEvent;
import rocks.cleanstone.game.chat.event.PlayerIssuedCommandEvent;
import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.inbound.ChatMessagePacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerID;
import rocks.cleanstone.player.PlayerManager;

public class IncomingChatPacketListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final PlayerManager playerManager;

    public IncomingChatPacketListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @EventListener
    @Async("chatExec")
    public void onChatMessage(InboundPacketEvent inboundPacketEvent) {
        if (inboundPacketEvent.getPacket() instanceof ChatMessagePacket) {
            Player player = playerManager.getOnlinePlayer(inboundPacketEvent.getConnection());
            PlayerID playerID = player.getId();
            String playerName = playerID.getName() + "(" + playerID.getUUID() + ")";

            ChatMessagePacket chatMessagePacket = ((ChatMessagePacket) inboundPacketEvent.getPacket());
            String chatMessage =chatMessagePacket.getMessage();

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
}
