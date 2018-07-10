package rocks.cleanstone.player.terminate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

import rocks.cleanstone.net.packet.outbound.PlayerListItemPacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.player.event.AsyncPlayerTerminationEvent;

public class TablistPackets {

    private final PlayerManager playerManager;

    @Autowired
    public TablistPackets(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Order(value = 30)
    @EventListener
    public void onTerminate(AsyncPlayerTerminationEvent e) {
        Player player = e.getPlayer();
        broadcastRemoval(player, player);
    }

    public void broadcastRemoval(Player player, Player... broadcastExemptions) {
        PlayerListItemPacket packet = new PlayerListItemPacket(PlayerListItemPacket.Action.REMOVE_PLAYER);
        packet.getPlayers().add(new PlayerListItemPacket.PlayerItem(player));

        playerManager.broadcastPacket(packet, broadcastExemptions);
    }
}
