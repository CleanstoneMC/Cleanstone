package rocks.cleanstone.player.initialize;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.game.entity.Location;
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
        Entity entity = player.getEntity();
        Location location = entity.getLocation();

        SpawnPlayerPacket spawnPlayerPacket = new SpawnPlayerPacket(entity.getEntityID(), player.getId().getUUID(), location.getPosition().getX(), location.getPosition().getY(), location.getPosition().getZ(),
                location.getRotation().getYaw(), location.getRotation().getPitch(), null); //TODO: Add Metadata

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

                    Entity entity = player.getEntity();
                    Location location = entity.getLocation();

                    SpawnPlayerPacket spawnPlayerPacket = new SpawnPlayerPacket(entity.getEntityID(), onlinePlayer.getId().getUUID(), location.getPosition().getX(), location.getPosition().getY(), location.getPosition().getZ(),
                            location.getRotation().getYaw(), location.getRotation().getPitch(), null); //TODO: Add Metadata

                    player.sendPacket(spawnPlayerPacket);
                });
    }
}
