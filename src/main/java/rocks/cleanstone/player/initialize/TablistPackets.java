package rocks.cleanstone.player.initialize;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import rocks.cleanstone.net.minecraft.packet.outbound.PlayerListItemPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.SpawnPlayerPacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.player.event.AsyncPlayerInitializationEvent;

public class TablistPackets {

    private final PlayerManager playerManager;

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


        // This sends the Player Spawn Packet for every Player to the Joined User
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

    public void broadcastAddition(Player player, Player... broadcastExemptions) {
        PlayerListItemPacket packet = new PlayerListItemPacket(PlayerListItemPacket.Action.ADD_PLAYER);
        packet.getPlayers().add(new PlayerListItemPacket.PlayerItem(player));

        playerManager.broadcastPacket(packet, broadcastExemptions);

        // This sends the Spawn Packet for the Spawned User to the other Users
        SpawnPlayerPacket spawnPlayerPacket = new SpawnPlayerPacket(player.getEntity().getEntityID(), player.getId().getUUID(), player.getEntity().getPosition().getX(), player.getEntity().getPosition().getY(), player.getEntity().getPosition().getZ(),
                player.getEntity().getRotation().getYaw(), player.getEntity().getRotation().getPitch(), null); //TODO: Add Metadata

        playerManager.broadcastPacket(spawnPlayerPacket, player);
    }

}
