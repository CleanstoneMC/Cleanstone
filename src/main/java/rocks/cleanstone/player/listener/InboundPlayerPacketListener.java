package rocks.cleanstone.player.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.core.event.EventAction;
import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.event.PlayerInboundPacketEvent;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

@Component
public class InboundPlayerPacketListener {

    private final PlayerManager playerManager;

    @Autowired
    public InboundPlayerPacketListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Order(value = EventAction.MODIFY + 50)
    @EventListener
    public void onPacket(InboundPacketEvent<? extends Packet> inboundPacketEvent) {
        Player player = playerManager.getOnlinePlayer(inboundPacketEvent.getConnection());

        if (player == null || playerManager.isTerminating(player)) {
            return;
        }

        PlayerInboundPacketEvent<? extends Packet> playerEvent = new PlayerInboundPacketEvent<>(
                inboundPacketEvent.getPacket(), inboundPacketEvent.getConnection(),
                player, inboundPacketEvent.getNetworking());

        if (CleanstoneServer.publishEvent(playerEvent).isCancelled()) {
            inboundPacketEvent.cancel();
        }
    }
}