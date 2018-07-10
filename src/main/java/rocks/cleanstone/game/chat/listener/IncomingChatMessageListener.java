package rocks.cleanstone.game.chat.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import rocks.cleanstone.game.chat.ChatManager;
import rocks.cleanstone.game.chat.event.PlayerChatMessageEvent;
import rocks.cleanstone.game.chat.message.ChatMessage;

public class IncomingChatMessageListener {
    private ChatManager chatManager;

    @Autowired
    public IncomingChatMessageListener(ChatManager chatManager) {
        this.chatManager = chatManager;
    }

    @EventListener
    @Async("chatExec")
    public void onChatMessage(PlayerChatMessageEvent playerChatMessageEvent) {
        chatManager.sendChatMessage(
                new ChatMessage(playerChatMessageEvent.getPlayer(), playerChatMessageEvent.getMessage()));
    }
}
