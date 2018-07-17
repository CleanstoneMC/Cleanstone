package rocks.cleanstone.player.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import rocks.cleanstone.game.entity.HeadRotatablePosition;
import rocks.cleanstone.net.packet.outbound.EntityHeadLookPacket;
import rocks.cleanstone.net.packet.outbound.EntityLookAndRelativeMovePacket;
import rocks.cleanstone.net.packet.outbound.EntityLookPacket;
import rocks.cleanstone.net.packet.outbound.EntityRelativeMovePacket;
import rocks.cleanstone.net.packet.outbound.EntityTeleportPacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.player.event.PlayerMoveEvent;

public class PlayerMoveListener {
    private final PlayerManager playerManager;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public PlayerMoveListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Async(value = "playerExec")
    @EventListener
    public void onPlayerMove(PlayerMoveEvent playerMoveEvent) {
        final HeadRotatablePosition oldPosition = playerMoveEvent.getOldPosition();
        final HeadRotatablePosition newPosition = playerMoveEvent.getNewPosition();

        final Player movingPlayer = playerMoveEvent.getPlayer();
        final int entityID = movingPlayer.getEntity().getEntityID();

        final int pitch = newPosition.getRotation().getIntPitch();
        final int yaw = newPosition.getRotation().getIntYaw();

        if (!oldPosition.getHeadRotation().equals(newPosition.getHeadRotation())) {
            EntityHeadLookPacket entityHeadLookPacket = new EntityHeadLookPacket(
                    entityID, newPosition.getHeadRotation().getIntYaw());
            playerManager.broadcastPacket(entityHeadLookPacket, movingPlayer);
        }

        if (oldPosition.equals(newPosition)) {
            if (oldPosition.getRotation().equals(newPosition.getRotation())) {
                return;
            }

            EntityLookPacket entityLookPacket = new EntityLookPacket(entityID, yaw, pitch, movingPlayer.isFlying());

            playerManager.broadcastPacket(entityLookPacket, movingPlayer);
            return;
        }

        final double deltaX = (newPosition.getX() * 32 - oldPosition.getX() * 32) * 128;
        final double deltaY = (newPosition.getY() * 32 - oldPosition.getY() * 32) * 128;
        final double deltaZ = (newPosition.getZ() * 32 - oldPosition.getZ() * 32) * 128;


        boolean teleport = deltaX > Short.MAX_VALUE || deltaY > Short.MAX_VALUE || deltaZ > Short.MAX_VALUE
                || deltaX < Short.MIN_VALUE || deltaY < Short.MIN_VALUE || deltaZ < Short.MIN_VALUE;

        if (teleport) {
            EntityTeleportPacket entityTeleportPacket = new EntityTeleportPacket(entityID, newPosition.getX(),
                    newPosition.getY(), newPosition.getZ(), yaw, pitch, movingPlayer.isFlying());
            // TODO viewers show teleported entity in wrong location
            playerManager.broadcastPacket(entityTeleportPacket, movingPlayer);
            return;
        }

        if (oldPosition.getRotation().equals(newPosition.getRotation())) {
            EntityRelativeMovePacket entityRelativeMovePacket = new EntityRelativeMovePacket(entityID,
                    ((short) deltaX), ((short) deltaY), ((short) deltaZ), movingPlayer.isFlying());

            playerManager.broadcastPacket(entityRelativeMovePacket, movingPlayer);
            return;
        }

        EntityLookAndRelativeMovePacket entityLookAndRelativeMovePacket = new EntityLookAndRelativeMovePacket(
                entityID, ((short) deltaX), ((short) deltaY), ((short) deltaZ), yaw, pitch, movingPlayer.isFlying());

        playerManager.broadcastPacket(entityLookAndRelativeMovePacket, movingPlayer);
    }
}
