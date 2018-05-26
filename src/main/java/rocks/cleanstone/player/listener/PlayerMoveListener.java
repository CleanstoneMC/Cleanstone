package rocks.cleanstone.player.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.entity.EntityMoveEvent;
import rocks.cleanstone.game.entity.Rotation;
import rocks.cleanstone.game.entity.vanilla.Human;
import rocks.cleanstone.net.minecraft.packet.outbound.EntityLookAndRelativeMovePacket;
import rocks.cleanstone.net.minecraft.packet.outbound.EntityLookPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.EntityRelativeMovePacket;
import rocks.cleanstone.net.minecraft.packet.outbound.EntityTeleportPacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.player.event.PlayerMoveEvent;

public class PlayerMoveListener {
    private final PlayerManager playerManager;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public PlayerMoveListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Async(value = "playerExec")
    @EventListener
    public void onPlayerMove(PlayerMoveEvent playerMoveEvent) {
        final Position oldPosition = playerMoveEvent.getOldPosition();
        final Position newPosition = playerMoveEvent.getNewPosition();
        final Rotation oldRotation = playerMoveEvent.getOldRotation();
        final Rotation newRotation = playerMoveEvent.getNewRotation();

        final Player movingPlayer = playerMoveEvent.getPlayer();
        final int entityID = movingPlayer.getEntity().getEntityID();

        final int pitch = newRotation.getIntPitch();
        final int yaw = newRotation.getIntYaw();

        if (oldPosition.equals(newPosition)) {
            if (oldRotation.equals(newRotation)) {
                return;
            }

            EntityLookPacket entityLookPacket = new EntityLookPacket(entityID, yaw, pitch, true); //TODO: Add onGround

            playerManager.broadcastPacket(entityLookPacket, movingPlayer);
            return;
        }

        final double deltaX = (newPosition.getX() * 32 - oldPosition.getX() * 32) * 128;
        final double deltaY = (newPosition.getY() * 32 - oldPosition.getY() * 32) * 128;
        final double deltaZ = (newPosition.getZ() * 32 - oldPosition.getZ() * 32) * 128;


        boolean teleport = deltaX > Short.MAX_VALUE || deltaY > Short.MAX_VALUE || deltaZ > Short.MAX_VALUE
                || deltaX < Short.MIN_VALUE || deltaY < Short.MIN_VALUE || deltaZ < Short.MIN_VALUE;

        if (teleport) {
            EntityTeleportPacket entityTeleportPacket = new EntityTeleportPacket(entityID, deltaX, deltaY, deltaZ, yaw, pitch, true); //TODO: Add onGround

            playerManager.broadcastPacket(entityTeleportPacket, movingPlayer);
            return;
        }

        if (oldRotation.equals(newRotation)) {
            EntityRelativeMovePacket entityRelativeMovePacket = new EntityRelativeMovePacket(entityID, ((short) deltaX), ((short) deltaY), ((short) deltaZ), true); //TODO: Add onGround

            playerManager.broadcastPacket(entityRelativeMovePacket, movingPlayer);
            return;
        }

        EntityLookAndRelativeMovePacket entityLookAndRelativeMovePacket = new EntityLookAndRelativeMovePacket(
                entityID, ((short) deltaX), ((short) deltaY), ((short) deltaZ), yaw, pitch, true); //TODO: Add onGround

        playerManager.broadcastPacket(entityLookAndRelativeMovePacket, movingPlayer);
    }
}
