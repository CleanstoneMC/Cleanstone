package rocks.cleanstone.player.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.entity.vanilla.Human;
import rocks.cleanstone.game.entity.vanilla.SimpleHuman;
import rocks.cleanstone.game.world.EntityManager;
import rocks.cleanstone.net.packet.outbound.DestroyEntitiesPacket;
import rocks.cleanstone.net.packet.outbound.OutPlayerPositionAndLookPacket;
import rocks.cleanstone.net.packet.outbound.SpawnPlayerPacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.player.event.PlayerTeleportEvent;

import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class PlayerTeleportListener {
    private final PlayerManager playerManager;
    private final EntityManager entityManager;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public PlayerTeleportListener(PlayerManager playerManager, EntityManager entityManager) {
        this.playerManager = playerManager;
        this.entityManager = entityManager;
    }

    @Async(value = "playerExec")
    @EventListener
    public void onPlayerTeleport(PlayerTeleportEvent playerTeleportEvent) {

        if (playerTeleportEvent.isCancelled()) {
            return;
        }

        Player player = playerTeleportEvent.getPlayer();

        Human human = new SimpleHuman(player.getEntity().getWorld(), playerTeleportEvent.getNewPosition());

        entityManager.addEntityWithoutID(human);
        player.setEntity(human);
        entityManager.removeEntity(player.getEntity());

        DestroyEntitiesPacket destroyEntitiesPacket = new DestroyEntitiesPacket(
                Collections.singletonList(player.getEntity().getEntityID()));
        playerManager.broadcastPacket(destroyEntitiesPacket);

        OutPlayerPositionAndLookPacket playerPositionAndLookPacket = new OutPlayerPositionAndLookPacket(playerTeleportEvent.getNewPosition(), 0,
                ThreadLocalRandom.current().nextInt());
        player.sendPacket(playerPositionAndLookPacket);

        SpawnPlayerPacket spawnPlayerPacket = new SpawnPlayerPacket(human.getEntityID(),
                player.getID().getUUID(), playerTeleportEvent.getNewPosition(), null); //TODO: Add Metadata

        playerManager.broadcastPacket(spawnPlayerPacket, player);
    }
}
