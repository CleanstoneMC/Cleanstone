package rocks.cleanstone.game.chat;

import rocks.cleanstone.game.chat.message.Chat;
import rocks.cleanstone.game.chat.message.ChatMessage;
import rocks.cleanstone.net.minecraft.packet.enums.ChatPosition;
import rocks.cleanstone.net.minecraft.packet.outbound.OutChatMessagePacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

public class ChatManager {
    private final PlayerManager playerManager;

    public ChatManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    public void sendChatMessage(ChatMessage message, Player... excludedPlayers) {
        OutChatMessagePacket chatMessagePacket = new OutChatMessagePacket(message, ChatPosition.CHAT);

        playerManager.broadcastPacket(chatMessagePacket, excludedPlayers);
    }

    public void sendChatMessage(Player receiver,Chat message) {
        receiver.sendPacket(new OutChatMessagePacket(message, ChatPosition.CHAT));
    }
}
