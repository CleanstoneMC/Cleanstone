package rocks.cleanstone.net.event.packet.inbound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.core.event.EventAction;
import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.event.PlayerInboundPacketEvent;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

@Component
public class InboundPlayerPacketListener extends InboundPacketEventListener {

    private final PlayerManager playerManager;

    @Autowired
    public InboundPlayerPacketListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Order(value = EventAction.MODIFY + 50)
    @EventListener
    @Override
    public void onPacket(InboundPacketEvent inboundPacketEvent) {
        Player player = playerManager.getOnlinePlayer(inboundPacketEvent.getConnection());

        if (player == null || playerManager.isTerminating(player)) {
            return;
        }

        PlayerInboundPacketEvent playerEvent = new PlayerInboundPacketEvent<>(
                inboundPacketEvent.getPacket(), inboundPacketEvent.getConnection(),
                player, inboundPacketEvent.getNetworking());

        if (CleanstoneServer.publishEvent(playerEvent).isCancelled()) {
            inboundPacketEvent.cancel();
        }
    }
}