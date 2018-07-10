package rocks.cleanstone.player.terminate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

import java.util.Collections;

import rocks.cleanstone.net.packet.outbound.DestroyEntitiesPacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.player.event.AsyncPlayerTerminationEvent;

public class DestroyPlayerPackets {

    private final PlayerManager playerManager;

    @Autowired
    public DestroyPlayerPackets(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Order(value = 25)
    @EventListener
    public void onJoin(AsyncPlayerTerminationEvent e) {
        Player player = e.getPlayer();
        broadcastRemoval(player, player);
    }


    public void broadcastRemoval(Player player, Player... broadcastExemptions) {
        DestroyEntitiesPacket packet = new DestroyEntitiesPacket(
                Collections.singletonList(player.getEntity().getEntityID()));
        playerManager.broadcastPacket(packet, broadcastExemptions);
    }
}
