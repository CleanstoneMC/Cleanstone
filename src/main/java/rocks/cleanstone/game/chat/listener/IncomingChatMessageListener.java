package rocks.cleanstone.game.chat.listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import rocks.cleanstone.game.chat.ChatManager;
import rocks.cleanstone.game.chat.event.PlayerChatMessageEvent;
import rocks.cleanstone.game.chat.message.Chat;
import rocks.cleanstone.game.chat.message.ChatMessage;
import rocks.cleanstone.player.PlayerManager;

public class IncomingChatMessageListener {
    private ChatManager chatManager;

    public IncomingChatMessageListener(ChatManager chatManager) {
        this.chatManager = chatManager;
    }

    @EventListener
    @Async("chatExec")
    public void onChatMessage(PlayerChatMessageEvent playerChatMessageEvent) {
        chatManager.sendChatMessage(new ChatMessage(playerChatMessageEvent.getPlayer(), playerChatMessageEvent.getMessage()));
    }
}
