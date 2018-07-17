package rocks.cleanstone.player.initialize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.game.entity.RotatablePosition;
import rocks.cleanstone.net.packet.outbound.SpawnPlayerPacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.player.event.AsyncPlayerInitializationEvent;

public class SpawnPlayerPackets {

    private final PlayerManager playerManager;

    @Autowired
    public SpawnPlayerPackets(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Order(value = 35)
    @EventListener
    public void onJoin(AsyncPlayerInitializationEvent e) {
        Player player = e.getPlayer();

        sendToJoined(player);
        sendToOther(player);
    }

    /**
     * This sends the Spawn Packet for the Spawned User to the other Users
     */
    private void sendToOther(Player player) {
        Entity entity = player.getEntity();
        RotatablePosition position = entity.getPosition();

        SpawnPlayerPacket spawnPlayerPacket = new SpawnPlayerPacket(entity.getEntityID(),
                player.getID().getUUID(), position, null); //TODO: Add Metadata

        playerManager.broadcastPacket(spawnPlayerPacket, player);
    }

    /**
     * This sends the Player Spawn Packet for every Player to the Joined User
     */
    private void sendToJoined(Player player) {
        playerManager.getOnlinePlayers().forEach(
                onlinePlayer -> {
                    if (onlinePlayer == player) {
                        return;
                    }

                    Entity entity = onlinePlayer.getEntity();
                    RotatablePosition position = entity.getPosition();

                    SpawnPlayerPacket spawnPlayerPacket = new SpawnPlayerPacket(entity.getEntityID(),
                            onlinePlayer.getID().getUUID(), position, null); //TODO: Add Metadata

                    player.sendPacket(spawnPlayerPacket);
                });
    }
}
