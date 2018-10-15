package rocks.cleanstone.game.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.chat.message.ChatMessage;
import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.net.minecraft.packet.outbound.OutChatMessagePacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

@Component("chatManager")
public class ChatManager {
    private final PlayerManager playerManager;

    @Autowired
    public ChatManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    public void sendChatMessage(ChatMessage message, Player... excludedPlayers) {
        final OutChatMessagePacket chatMessagePacket = new OutChatMessagePacket(message, ChatPosition.CHAT);

        playerManager.broadcastPacket(chatMessagePacket, excludedPlayers);
    }

    public void sendChatMessage(Player receiver, Text message) {
        receiver.sendPacket(new OutChatMessagePacket(message, ChatPosition.CHAT));
    }
}
