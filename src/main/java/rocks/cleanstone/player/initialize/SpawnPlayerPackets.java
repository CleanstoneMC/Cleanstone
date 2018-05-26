package rocks.cleanstone.player.initialize;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import rocks.cleanstone.net.minecraft.packet.outbound.SpawnPlayerPacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.player.event.AsyncPlayerInitializationEvent;

public class SpawnPlayerPackets {

    private final PlayerManager playerManager;

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
        SpawnPlayerPacket spawnPlayerPacket = new SpawnPlayerPacket(player.getEntity().getEntityID(), player.getId().getUUID(), player.getEntity().getPosition().getX(), player.getEntity().getPosition().getY(), player.getEntity().getPosition().getZ(),
                player.getEntity().getRotation().getYaw(), player.getEntity().getRotation().getPitch(), null); //TODO: Add Metadata

        playerManager.broadcastPacket(spawnPlayerPacket, player);
    }

    /**
     *  This sends the Player Spawn Packet for every Player to the Joined User
     */
    private void sendToJoined(Player player) {
        playerManager.getOnlinePlayers().forEach(
                onlinePlayer -> {
                    if (onlinePlayer == player) {
                        return;
                    }

                    SpawnPlayerPacket spawnPlayerPacket = new SpawnPlayerPacket(onlinePlayer.getEntity().getEntityID(), onlinePlayer.getId().getUUID(), onlinePlayer.getEntity().getPosition().getX(), onlinePlayer.getEntity().getPosition().getY(), onlinePlayer.getEntity().getPosition().getZ(),
                            onlinePlayer.getEntity().getRotation().getYaw(), onlinePlayer.getEntity().getRotation().getPitch(), null); //TODO: Add Metadata

                    player.sendPacket(spawnPlayerPacket);
                });
    }
}
