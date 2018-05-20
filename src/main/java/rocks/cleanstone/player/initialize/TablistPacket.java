package rocks.cleanstone.player.initialize;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

import rocks.cleanstone.net.minecraft.packet.outbound.PlayerListItemPacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.player.event.AsyncPlayerInitializationEvent;

public class TablistPacket {

    private final PlayerManager playerManager;

    public TablistPacket(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Order(value = 30)
    @EventListener
    public void onInitialize(AsyncPlayerInitializationEvent e) {
        Player player = e.getPlayer();
        PlayerListItemPacket packet = new PlayerListItemPacket(PlayerListItemPacket.Action.ADD_PLAYER);

        playerManager.getOnlinePlayers().forEach(
                onlinePlayer -> packet.getPlayers().add(new PlayerListItemPacket.PlayerItem(onlinePlayer)));

        player.sendPacket(packet);
    }
}
