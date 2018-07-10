package rocks.cleanstone.game.chat.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.chat.event.PlayerTabCompleteEvent;
import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.packet.inbound.InTabCompletePacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

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

        Player player = playerManager.getOnlinePlayer(inboundPacketEvent.getConnection());
        if (player == null) return;

        InTabCompletePacket tabCompletePacket = (InTabCompletePacket) inboundPacketEvent.getPacket();

        String text = tabCompletePacket.getText();
        if (text.charAt(0) != '/' && !tabCompletePacket.isAssumeCommand()) return;

        CleanstoneServer.publishEvent(new PlayerTabCompleteEvent(
                player,
                tabCompletePacket.getText(),
                tabCompletePacket.getLookedAtBlock()
        ));
    }
}
