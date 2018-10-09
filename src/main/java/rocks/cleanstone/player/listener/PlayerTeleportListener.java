package rocks.cleanstone.player.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.entity.vanilla.Human;
import rocks.cleanstone.game.entity.vanilla.SimpleHuman;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.net.minecraft.packet.outbound.DestroyEntitiesPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.OutPlayerPositionAndLookPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.SpawnPlayerPacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.player.event.PlayerTeleportEvent;

import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class PlayerTeleportListener {
    private final Logger logger = LoggerFactory.getLogger(PlayerTeleportListener.class);
    private final PlayerManager playerManager;

    @Autowired
    public PlayerTeleportListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Async(value = "playerExec")
    @EventListener
    public void onPlayerTeleport(PlayerTeleportEvent playerTeleportEvent) {
        Player player = playerTeleportEvent.getPlayer();
        Human oldHuman = player.getEntity();
        World world = oldHuman.getWorld();
        Human newHuman = new SimpleHuman(world, playerTeleportEvent.getNewPosition());

        // TODO consider thread-safety with getting/changing player entities
        logger.debug("{} now has entity id {}", player.getName(), newHuman.getEntityID());
        player.setEntity(newHuman);
        world.getEntityRegistry().removeEntity(oldHuman);
        world.getEntityRegistry().addEntity(newHuman);

        DestroyEntitiesPacket destroyEntitiesPacket = new DestroyEntitiesPacket(
                Collections.singletonList(oldHuman.getEntityID()));
        playerManager.broadcastPacket(destroyEntitiesPacket);

        OutPlayerPositionAndLookPacket playerPositionAndLookPacket = new OutPlayerPositionAndLookPacket(playerTeleportEvent.getNewPosition(), 0,
                ThreadLocalRandom.current().nextInt());
        player.sendPacket(playerPositionAndLookPacket);

        SpawnPlayerPacket spawnPlayerPacket = new SpawnPlayerPacket(newHuman.getEntityID(),
                player.getID().getUUID(), playerTeleportEvent.getNewPosition(), null); //TODO: Add Metadata

        playerManager.broadcastPacket(spawnPlayerPacket, player);
    }
}
