package rocks.cleanstone.player.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.core.event.EventAction;
import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.event.OutboundPacketEvent;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.player.event.PlayerInboundPacketEvent;
import rocks.cleanstone.player.event.PlayerOutboundPacketEvent;

public class PlayerPacketListener {

    private final PlayerManager playerManager;

    @Autowired
    public PlayerPacketListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Order(value = EventAction.MODIFY + 50)
    @EventListener
    public void onInboundPacket(InboundPacketEvent e) {
        Player player = playerManager.getOnlinePlayer(e.getConnection());
        if (player == null || playerManager.isTerminating(player)) return;

        PlayerInboundPacketEvent playerEvent = new PlayerInboundPacketEvent(e.getPacket(),
                e.getConnection(), player, e.getNetworking());
        if (CleanstoneServer.publishEvent(playerEvent).isCancelled()) e.cancel();
    }

    @Order(value = EventAction.MODIFY + 50)
    @EventListener
    public void onOutboundPacket(OutboundPacketEvent e) {
        Player player = playerManager.getOnlinePlayer(e.getConnection());
        if (player == null || playerManager.isTerminating(player)) return;

        PlayerOutboundPacketEvent playerEvent = new PlayerOutboundPacketEvent(e.getPacket(),
                e.getConnection(), player, e.getNetworking());
        if (CleanstoneServer.publishEvent(playerEvent).isCancelled()) e.cancel();
    }
}
