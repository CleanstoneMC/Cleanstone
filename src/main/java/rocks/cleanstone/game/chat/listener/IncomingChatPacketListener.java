package rocks.cleanstone.game.chat.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.chat.event.PlayerChatMessageEvent;
import rocks.cleanstone.game.chat.event.PlayerIssuedCommandEvent;
import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.inbound.ChatMessagePacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerID;
import rocks.cleanstone.player.PlayerManager;

import java.util.Arrays;

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
            Player player = playerManager.getPlayerByConnection(inboundPacketEvent.getConnection());
            PlayerID playerID = player.getId();
            String playerName = playerID.getName() + "(" + playerID.getUUID() + ")";

            ChatMessagePacket chatMessagePacket = ((ChatMessagePacket) inboundPacketEvent.getPacket());
            String chatMessage =chatMessagePacket.getMessage();

            if (chatMessage.charAt(0) == '/') {
                //Command

                String[] commandSplit = chatMessage.split(" ");
                String[] commandArgs = Arrays.copyOfRange(commandSplit, 1, commandSplit.length);
                String command = commandSplit[0].substring(1);

                logger.info("Command from {}: {}", playerName, chatMessage);

                CleanstoneServer.publishEvent(new PlayerIssuedCommandEvent(player, command, commandArgs));
            } else {
                logger.info("Message from {}: {}", playerName, chatMessage);

                CleanstoneServer.publishEvent(new PlayerChatMessageEvent(player, chatMessage));
            }
        }
    }
}
