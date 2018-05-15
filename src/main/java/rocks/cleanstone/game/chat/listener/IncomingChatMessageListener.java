package rocks.cleanstone.game.chat.listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import rocks.cleanstone.game.chat.event.PlayerChatMessageEvent;
import rocks.cleanstone.game.chat.message.Chat;
import rocks.cleanstone.player.PlayerManager;

public class IncomingChatMessageListener {

    private final PlayerManager playerManager;

    public IncomingChatMessageListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @EventListener
    @Async("chatExec")
    public void onChatMessage(PlayerChatMessageEvent playerChatMessageEvent) {
        playerManager.getOnlinePlayers().forEach(player -> {
            String chatMessage = playerChatMessageEvent.getPlayer().getId().getName() + ": " + playerChatMessageEvent.getMessage(); //TODO: Chat Manager

            player.sendMessage(new Chat(chatMessage));
        });
    }
}
