package rocks.cleanstone.player.initialize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

import rocks.cleanstone.net.packet.outbound.PlayerListItemPacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.player.event.AsyncPlayerInitializationEvent;

public class TablistPackets {

    private final PlayerManager playerManager;

    @Autowired
    public TablistPackets(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Order(value = 30)
    @EventListener
    public void onInitialize(AsyncPlayerInitializationEvent e) {
        Player player = e.getPlayer();

        sendAllTo(player);
        broadcastAddition(player, player);
    }

    public void sendAllTo(Player player) {
        PlayerListItemPacket packet = new PlayerListItemPacket(PlayerListItemPacket.Action.ADD_PLAYER);

        playerManager.getOnlinePlayers().forEach(
                onlinePlayer -> packet.getPlayers().add(new PlayerListItemPacket.PlayerItem(onlinePlayer)));

        player.sendPacket(packet);
    }

    public void broadcastAddition(Player player, Player... broadcastExemptions) {
        PlayerListItemPacket packet = new PlayerListItemPacket(PlayerListItemPacket.Action.ADD_PLAYER);
        packet.getPlayers().add(new PlayerListItemPacket.PlayerItem(player));

        playerManager.broadcastPacket(packet, broadcastExemptions);
    }

}
