package rocks.cleanstone.player.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.entity.HeadRotatablePosition;
import rocks.cleanstone.game.entity.vanilla.Human;
import rocks.cleanstone.game.entity.vanilla.SimpleHuman;
import rocks.cleanstone.game.world.EntityManager;
import rocks.cleanstone.net.packet.outbound.OutPlayerPositionAndLookPacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.player.event.PlayerMoveEvent;
import rocks.cleanstone.player.event.PlayerTeleportEvent;

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

        PlayerMoveEvent event = new PlayerMoveEvent(player, new HeadRotatablePosition(playerTeleportEvent.getOldPosition()),
                playerTeleportEvent.getNewPosition(), playerTeleportEvent.getMoveReason());

        if (CleanstoneServer.publishEvent(event).isCancelled()) {
            return;
        }

        player.getEntity().setPosition(playerTeleportEvent.getNewPosition());

        OutPlayerPositionAndLookPacket packet = new OutPlayerPositionAndLookPacket(playerTeleportEvent.getNewPosition(), 0,
                ThreadLocalRandom.current().nextInt());

        player.sendPacket(packet);
    }
}
