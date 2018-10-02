package rocks.cleanstone.player.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.core.event.EventAction;
import rocks.cleanstone.net.event.OutboundPacketEvent;
import rocks.cleanstone.net.event.PlayerOutboundPacketEvent;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

@Component
public class OutboundPlayerPacketListener {

    private final PlayerManager playerManager;

    @Autowired
    public OutboundPlayerPacketListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Order(value = EventAction.MODIFY + 50)
    @EventListener
    public void onPacket(OutboundPacketEvent outboundPacketEvent) {
        Player player = playerManager.getOnlinePlayer(outboundPacketEvent.getConnection());

        if (player == null || playerManager.isTerminating(player)) {
            return;
        }

        PlayerOutboundPacketEvent playerEvent = new PlayerOutboundPacketEvent<>(
                outboundPacketEvent.getPacket(), outboundPacketEvent.getConnection(),
                player, outboundPacketEvent.getNetworking());

        if (CleanstoneServer.publishEvent(playerEvent).isCancelled()) {
            outboundPacketEvent.cancel();
        }
    }
}
