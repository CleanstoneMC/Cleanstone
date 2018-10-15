package rocks.cleanstone.game.chat.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.chat.event.PlayerTabCompleteEvent;
import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.inbound.InTabCompletePacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

@Component
public class IncomingTabCompleteListener {
    private final PlayerManager playerManager;

    @Autowired
    public IncomingTabCompleteListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Async("chatExec")
    @EventListener
    public void onChatMessage(InboundPacketEvent inboundPacketEvent) {
        if (!(inboundPacketEvent.getPacket() instanceof InTabCompletePacket)) {
            return;
        }

        final Player player = playerManager.getOnlinePlayer(inboundPacketEvent.getConnection());
        if (player == null) return;

        final InTabCompletePacket tabCompletePacket = (InTabCompletePacket) inboundPacketEvent.getPacket();

        final String text = tabCompletePacket.getText();
        if (text.charAt(0) != '/' && !tabCompletePacket.isAssumeCommand()) return;

        CleanstoneServer.publishEvent(new PlayerTabCompleteEvent(
                player,
                tabCompletePacket.getText(),
                tabCompletePacket.getLookedAtBlock()
        ));
    }
}
