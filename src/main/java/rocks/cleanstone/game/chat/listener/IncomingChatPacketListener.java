package rocks.cleanstone.game.chat.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.Identity;
import rocks.cleanstone.game.chat.event.PlayerChatMessageEvent;
import rocks.cleanstone.game.chat.event.PlayerIssuedCommandEvent;
import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.inbound.InChatMessagePacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

@Slf4j
@Component
public class IncomingChatPacketListener {
    private final PlayerManager playerManager;

    @Autowired
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
        if (player == null) {
            return;
        }
        Identity playerID = player.getID();
        String playerName = playerID.getName() + "(" + playerID.getUUID() + ")";

        InChatMessagePacket chatMessagePacket = ((InChatMessagePacket) inboundPacketEvent.getPacket());
        String chatMessage = chatMessagePacket.getMessage();
        if (chatMessage.isEmpty()) {
            return;
        }
        if (chatMessage.charAt(0) == '/') {
            //Command

            log.info("Command from {}: {}", playerName, chatMessage);

            CleanstoneServer.publishEvent(new PlayerIssuedCommandEvent(player, chatMessage));
        } else {
            log.info("Message from {}: {}", playerName, chatMessage);

            CleanstoneServer.publishEvent(new PlayerChatMessageEvent(player, chatMessage));
        }
    }
}
